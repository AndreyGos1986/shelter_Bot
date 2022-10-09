package jd5.ShelterBot.shelterBot.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Класс Волонтёр
 */
@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;
    private VolunteerSpecialization specialization; //Устанавливается из перечисления VolunteerSpecialization по типу животного,
                                                    //либо ставится по умолчанию, когда пользователю надо
                                                    //"просто спросить"

    @OneToMany (mappedBy = "volunteer")
    private List<Pet> pet;

    public Volunteer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public VolunteerSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(VolunteerSpecialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return id.equals(volunteer.id) && firstname.equals(volunteer.firstname) && surname.equals(volunteer.surname) && specialization == volunteer.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, specialization);
    }

    @Override
    public String
    toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", specialization=" + specialization +
                '}';
    }
}
