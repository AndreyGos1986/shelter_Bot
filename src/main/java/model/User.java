package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Класс пользователя общий
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private long telegramId;
    private String firstName;
    private String lastName;
    private UserType type; //тип пользователя ставится из перечисления UserType

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long userId) {
        this.telegramId = userId;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return telegramId == user.telegramId && id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telegramId, firstName, lastName, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telegramId=" + telegramId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                '}';
    }
}
