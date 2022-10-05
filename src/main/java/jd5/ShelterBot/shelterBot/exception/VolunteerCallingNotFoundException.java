package jd5.ShelterBot.shelterBot.exception;

public class VolunteerCallingNotFoundException extends RuntimeException {

    public VolunteerCallingNotFoundException() {
        System.out.println("Отсутствует в БД");
    }
}
