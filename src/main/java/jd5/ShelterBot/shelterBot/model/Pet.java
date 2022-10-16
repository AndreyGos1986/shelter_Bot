package jd5.ShelterBot.shelterBot.model;

import javax.persistence.*;
import java.util.Objects;
/**
 * Класс животное
 */
@Entity
public class Pet {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id; //идентификатор
    private String nickName; // кличка
    private int age; // возраст
    private String ration; //рацион
    private String behavior; //поведение
    private String health; // состояние здоровья
    private PetType type; // вид животного (выбирается из перечисления)
    @ManyToOne
    @JoinColumn
    private Volunteer volunteer;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && id.equals(pet.id) && nickName.equals(pet.nickName) && Objects.equals(ration, pet.ration) && behavior.equals(pet.behavior) && Objects.equals(health, pet.health) && type == pet.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, age, ration, behavior, health, type);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", ration='" + ration + '\'' +
                ", behavior='" + behavior + '\'' +
                ", health='" + health + '\'' +
                ", type=" + type +
                '}';
    }
}