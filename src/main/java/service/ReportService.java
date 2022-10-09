package service;

import model.Report;
import model.ReportStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис доклада/отчета усыновителя
 */
public interface ReportService {
    void addPhoto(byte[] data, Long fromUser, LocalDate date); // добавить фото

    void addRation(String text, Long fromUser, LocalDate date); // добавить рацион

    void addHealth(String text, Long fromUser, LocalDate date); //добавить состояние здоровья

    void addBehavior(String text, Long fromUser, LocalDate date); // добавить поведение

    Report findReportById(long reportId); // найти доклад по идентификатору

    List<Report> findAllReports(); // вывести все отчеты

    List<Report> findReportsWithStatus(ReportStatus status); //получить все отчеты по определённому статусу

    List<Report> findWrongReports(); //получить все некорректные отчеты

    byte[] getReportPhoto(Long reportId); //Поулчить фото по идентификатору отчета

    void sendMessageToUser(Long userId, String message); // Отправить сообщение пользователю

    boolean isReportingAllowed(Long telegramId); // разрашено писать сообщение или нет

    List<Report> findAllReportsByParentId(ReportStatus status, long parentId); // поулчить все отчеты от одного усыновителя

    Report setReportStatus(long reportId, ReportStatus status); //изменить или установить статус отчету
                                                                //из допустимых значений в перечислении ReportStatus
}
