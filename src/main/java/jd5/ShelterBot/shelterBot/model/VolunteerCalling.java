package jd5.ShelterBot.shelterBot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Класс вызова волонтёра
 */
@Entity
public class VolunteerCalling {
    @Id
    @GeneratedValue
    private Long id; //идентификатор
    private String firstName;
    private String lastName;
    private String phoneNumber; //телефон вызывающего
    private Long parentId; //идентификатор усыновителя
    private Long userId; //идентификатор пользователя (общего)
    private String cause; //причина вызова
    private ReportStatus status = ReportStatus.NEW; //статус
    private UserType type; // тип вызывающего (новый пользователь, усыновитель)
    private ShelterType shelterType; //вызывающий по типу животного (кошки, собаки, грузыну, попугаи)
    private LocalDate date; // дата вызова

    public Long getUserId() {
        return userId;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
