package com.challenger.user.bean;

/**
 * Created by Maksym_Baziuk on 17.11.2015.
 */
public class RegistrationRequest {

    private String email;
    private String password;
    private int gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
