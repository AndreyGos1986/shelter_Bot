package jd5.ShelterBot.shelterBot.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import jd5.ShelterBot.shelterBot.service.UserService;
import model.ParentUser;
import jd5.ShelterBot.shelterBot.model.Report;
import jd5.ShelterBot.shelterBot.model.ReportStatus;
import model.User;
import jd5.ShelterBot.shelterBot.repository.ReportRepository;
import jd5.ShelterBot.shelterBot.service.ReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Имплементация сервиса ReportService
 */
public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;
    private final UserService userService;
    private final TelegramBot telegramBot;

    public ReportServiceImpl(ReportRepository repository, UserService userService, TelegramBot telegramBot) {
        this.repository = repository;
        this.userService = userService;
        this.telegramBot = telegramBot;
    }

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
     * Метод добавления нового отчёта
     * @param data
     * @param telegramId
     * @param date
     */
    @Override
    public void addPhoto(byte[] data, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setPhoto(data);
        repository.save(report);
    }

    /**
     * Метод добавления/указания рациона
     * @param text
     * @param telegramId
     * @param date
     */
    @Override
    public void addRation(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setRation(text);
        repository.save(report);
    }

    /**
     * Метод для добавления/указания состояния здоровья
     * @param text
     * @param telegramId
     * @param date
     */
    @Override
    public void addHealth(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setHealth(text);
        repository.save(report);
    }

    /**
     * Метод добавления/указания поведения
     * @param text
     * @param telegramId
     * @param date
     */
    @Override
    public void addBehavior(String text, Long telegramId, LocalDate date) {
        Report report = getReport(telegramId, date);

        report.setBehavior(text);
        repository.save(report);
    }

    /**
     * метод поиска отчёта по идентификатору
     * @param reportId
     * @return
     */
    @Override
    public Report findReportById(long reportId) {
        Optional<Report> report = repository.findById(reportId);
        return report.orElse(null);
    }

    /**
     * Метод для вывода все отчетов
     * @return
     */
    @Override
    public List<Report> findAllReports() {
        return repository.findAll();
    }

    /**
     * Метод, выводящий все отчеты, имеющие определённый статус
     * @param status
     * @return
     */
    @Override
    public List<Report> findReportsWithStatus(ReportStatus status) {
        List<Report> reports = repository.findAll();
        reports.removeIf(a -> a.getStatus() != status);
        return reports;
    }

    /**
     * Метод, проверяющий короректность направленного отчета
     * @param r
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
     * @return
     */
    @Override
    public List<Report> findWrongReports() {
        List<Report> reports = repository.findAll();
        reports.removeIf(a -> !isWrongReport(a));
        return reports;
    }

    /**
     * Метод, выводящий фотографию из определённого отчёта, полученного оп его идентификатору
     * @param reportId
     * @return
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
     * Метод для отправки сообщения пользователю
     * @param userId
     * @param text
     */
    @Override
    public void sendMessageToUser(Long userId, String text) {
       User user = userService.findUserById(userId);
        SendMessage message = new SendMessage(user.getTelegramId(), text);
        telegramBot.execute(message);
    }

    /**
     * Метод проверяющий допустимость направления отчета усыновителем.
     * @param telegramId
     * @return
     */
    @Override
    public boolean isReportingAllowed(Long telegramId) {
        return userService.findParentByTelegramId(telegramId) != null;
    }

    /**
     * Метод получения всех отчетов от одного усыновителя
     * @param status
     * @param parentId
     * @return
     */
    @Override
    public List<Report> findAllReportsByParentId(ReportStatus status, long parentId) {
        List<Report> reports = repository.findAllByParentId(parentId);
        reports.removeIf(a -> a.getStatus() != status);
        return reports;
    }

    /**
     * Метод изменения статуса отчета. Отчет находим по идентификатору и если найден, меняем
     * @param reportId
     * @param status
     * @return
     */
    @Override
    public Report setReportStatus(long reportId, ReportStatus status) {
        Report report = findReportById(reportId);
        report.setStatus(status);
        repository.save(report);
        return report;
    }
}
