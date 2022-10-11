package jd5.ShelterBot.shelterBot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Класс доклад/отчет (который направляет усыновитель в течение испытательного срока)
 */
@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id; // идентификатор
    private Long parentId; // идентификатор усыновителя

    @JsonIgnore
    private byte[] photo; //фото жиивотного
    private String ration; //рацион
    private String health; //состояние здоровья
    private String behavior; // поведение
    private ReportStatus status = ReportStatus.NEW; //статус доклада из перечисления
    private LocalDate date;  //дата доклада

    public Report() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}