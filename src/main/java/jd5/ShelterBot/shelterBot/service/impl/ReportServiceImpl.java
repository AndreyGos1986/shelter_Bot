package jd5.ShelterBot.shelterBot.service.impl;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import jd5.ShelterBot.shelterBot.message.MessageConstants;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.Report;
import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.repository.ReportRepository;
import jd5.ShelterBot.shelterBot.service.ReportService;
import jd5.ShelterBot.shelterBot.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Имплементация сервиса ReportService
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final UserService userService;
    private final TelegramBot telegramBot;

    public ReportServiceImpl(ReportRepository repository, UserService userService, TelegramBot telegramBot) {
        this.repository = repository;
        this.userService = userService;
        this.telegramBot = telegramBot;
    }

    /**
     * Метод получения отчета, принимающий в параметры
     *
     * @param telegramId telegram идентификатор усыновителя
     * @param date       и дату отчета, после чего осуществляется поиск отчета по усыновителю и дате И
     *                   он пустой, создаётся новый отчёт, которому присваиваются идентификатор усыновителя и дата
     * @return и возвращается этот отчёт
     */
    private Report getReport(Long telegramId, LocalDate date) {
        ParentUser parent = userService.findParentByTelegramId(telegramId);
        Report report = repository.getReportByParentIdAndDate(parent.getId(), date);
        if (report == null) {
            report = new Report();
            report.setParentId(parent.getId());
            report.setDate(date);
        }

        return report;
    }

    /**
     * Метод добавления фотографии в новый отчет, принимающий в параметры
     *
     * @param data       массив байт (фото)
     * @param telegramId телеграмм идентификаторв
     * @param date       дату, после чего осуществляется поиск (получение отчета по идентификатору и дате),
     *                   к которому прикрепляется фото
     */
    @Override
    public void addPhoto(byte[] data, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setPhoto(data);
        repository.save(report);
    }

    /**
     * Метод добавления/указания рациона в отчете
     *
     * @param text       описание рациона
     * @param telegramId телеграмм идентификатор
     * @param date       дату, после чего осуществляется поиск (получение отчета по идентификатору и дате) и
     *                   описание рациона сохраняется в полученном отчете
     */
    @Override
    public void addRation(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setRation(text);
        repository.save(report);
    }

    /**
     * Метод для добавления/указания состояния здоровья
     *
     * @param text       описание состояния здоровья
     * @param telegramId телеграмм идентификатор
     * @param date       дату, после чего осуществляется поиск (получение отчета по идентификатору и дате) и
     *                   описание состояния здоровья сохраняется в полученном отчете
     */
    @Override
    public void addHealth(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setHealth(text);
        repository.save(report);
    }

    /**
     * Метод добавления/указания поведения
     *
     * @param text       описание поведения
     * @param telegramId телеграмм идентификатор
     * @param date       дату, после чего осуществляется поиск (получение отчета по идентификатору и дате) и
     *                   описание поведения сохраняется в полученном отчете
     */
    @Override
    public void addBehavior(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setBehavior(text);
        repository.save(report);
    }

    /**
     * Метод поиска отчёта по идентификатору, прнинимающий в параметры
     *
     * @param reportId идентификатор отчёта и
     * @return возвращающий найденный отчет, либо null, если ничего не найдено
     */
    @Override
    public Report findReportById(long reportId) {
        Optional<Report> report = repository.findById(reportId);
        return report.orElse(null);
    }

    /**
     * Метод для вывода все отчетов
     *
     * @return возвращает все отчеты
     */
    @Override
    public List<Report> findAllReports() {
        return repository.findAll();
    }

    /**
     * Метод, выводящий все отчеты, имеющие определённый статус, принимающий в параметры
     *
     * @param status статус отчета
     * @return выводящий список отчётов с указанным статусом
     */
    @Override
    public List<Report> findReportsWithStatus(ReportStatus status) {
        List<Report> reports = repository.findAll();
        reports.removeIf(a -> a.getStatus() != status);
        return reports;
    }

    /**
     * Метод, проверяющий короректность направленного отчета, принимающий в параметры
     *
     * @param r отчет, осуществляет проверку на предмет того, все ли шаги выполнен
     * @return
     */
    private boolean isWrongReport(Report r) {
        boolean hasPhoto = r.getPhoto() != null;
        boolean hasRation = r.getRation() != null;
        boolean hasHealth = r.getHealth() != null;
        boolean hasBehavior = r.getBehavior() != null;

        return !hasPhoto || !hasRation || !hasHealth || !hasBehavior;
    }

    /**
     * Метод выводящий все некорректные отчеты
     *
     * @return возвращает список некорректных отчетов
     */
    @Override
    public List<Report> findWrongReports() {
        List<Report> reports = repository.findAll();
        reports.removeIf(a -> !isWrongReport(a));
        return reports;
    }

    /**
     * Метод, выводящий фотографию из определённого отчёта, полученного оп его идентификатору
     *
     * @param reportId принимает в параметры идентификатор отчёта, проверяет, если отчёт есть,
     *                 создаётся массив байт (фото), ссылающийся на фото из отчёта
     * @return которое выводится
     */
    @Override
    public byte[] getReportPhoto(Long reportId) {
        Optional<Report> report = repository.findById(reportId);
        if (report.isPresent()) {
            byte[] result = report.get().getPhoto();
            if (result != null) {
                return result;
            }
        }
        return new byte[0];
    }

    /**
     * Метод для отправки сообщения пользователю, принимающий
     *
     * @param userId идентификатор пользователя и
     * @param text   текст сообщения, после осуществляется поиск пользователя, создаётся сообщение из параметра
     *               и отправляется
     */
    @Override
    public void sendMessageToUser(Long userId, String text) {
        ShelterUser user = userService.findUserById(userId);
        SendMessage message = new SendMessage(user.getTelegramId(), MessageConstants.VOLUNTEER_MESSAGE + text);
        telegramBot.execute(message);
    }

    /**
     * Метод проверяющий допустимость направления отчета усыновителем.
     *
     * @param telegramId принимает в параметры телеграмм идентификатор усыновителя, если найден, то допускается.
     * @return
     */
    @Override
    public boolean isReportingAllowed(Long telegramId) {
        return userService.findParentByTelegramId(telegramId) != null;
    }

    /**
     * Метод получения всех отчетов от одного усыновителя с определённым статусом
     *
     * @param status   принимается определённый статус и
     * @param parentId идентификатор усыновителя
     * @return возвращает список отчетов от одного усыновителя с определённым статусом
     */
    @Override
    public List<Report> findAllReportsByParentId(ReportStatus status, long parentId) {
        List<Report> reports = repository.findAllByParentId(parentId);
        reports.removeIf(a -> a.getStatus() != status);
        return reports;
    }

    /**
     * Метод изменения статуса отчета. Принимает в параметры
     *
     * @param reportId идент-р отчета и
     * @param status   присваиваемый статус, после чего находится нужный отчет и ему присваивается статус
     * @return возвращая найденный отчёт с измененным статусом
     */
    @Override
    public Report setReportStatus(long reportId, ReportStatus status) {
        Report report = findReportById(reportId);
        report.setStatus(status);
        repository.save(report);
        return report;
    }
}
