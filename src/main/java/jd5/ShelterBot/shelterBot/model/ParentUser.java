package jd5.ShelterBot.shelterBot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

//Тут я бы убрал parentId и вместо него добавил бы ссылку на id в таблице пользователей.
//Еще надо где-то в базе хранить фотки из отчета, можно создать еще одну талбицу,
//в которой будут id, parentId и массив с фотками,а сюда добавить ссылку на id из таблицы с фотками


@Entity
public class ParentUser {

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;
    private Long shelterUserId;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate lastReportDate;
    private LocalDateTime lastNoticeDateAndTime;
    private LocalDate endOfProbation;


    public ParentUser(Long parentId,Long shelterUserId, String name, String surname, String phoneNumber) {
        this.parentId = parentId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.shelterUserId=shelterUserId;
//        Определиться сколько будет длиться испытательный срок
//        endOfProbation = LocalDate.now() +
    }

    public ParentUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(LocalDate lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public LocalDateTime getLastNoticeDateAndTime() {
        return lastNoticeDateAndTime;
    }

    public void setLastNoticeDateAndTime(LocalDateTime lastNoticeDateAndTime) {
        this.lastNoticeDateAndTime = lastNoticeDateAndTime;
    }

    public LocalDate getEndOfProbation() {
        return endOfProbation;
    }

    public void setEndOfProbation(LocalDate endOfProbation) {
        this.endOfProbation = endOfProbation;
    }

    public Long getShelterUserId() {
        return shelterUserId;
    }

    public void setShelterUserId(Long shelterUserId) {
        this.shelterUserId = shelterUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentUser that = (ParentUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
