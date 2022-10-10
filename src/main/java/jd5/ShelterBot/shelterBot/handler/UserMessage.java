package jd5.ShelterBot.shelterBot.handler;

import com.pengrad.telegrambot.model.Contact;
import com.pengrad.telegrambot.model.User;

public class UserMessage {
    private User user;
    private String message;
    private long userId = -1;
    private byte[] picture;
    private Contact contact;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
