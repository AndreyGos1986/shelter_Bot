package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class ParentUser {

    @Id
    @GeneratedValue
    private Long id;
    private Long UserId;
    private String phoneNumber;
    private LocalDateTime lastReportDate;
    private LocalDateTime lastNotification;
    private LocalDateTime probation;

    @OneToOne // хотя тут под вопросом, т.к. питомцев можно приютить и несколько
    private Pet pet;

    public ParentUser() {
        lastReportDate = LocalDateTime.now();
        probation = lastReportDate.plusDays(30);
    }

    public LocalDateTime getLastNotification() {
        return lastNotification;
    }

    public void setLastNotification(LocalDateTime lastNotification) {
        this.lastNotification = lastNotification;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDateTime getProbation() {
        return probation;
    }

    public void setProbation(LocalDateTime probation) {
        this.probation = probation;
    }

    public Long getShelterUserId() {
        return UserId;
    }

    public void setShelterUserId(Long shelterUserId) {
        this.UserId = shelterUserId;
    }

    public LocalDateTime getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(LocalDateTime lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
