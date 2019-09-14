package org.dmace.store.model.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RegisterBean {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=3, max=64)
    private String name;

    @NotEmpty
    private String phone;

    @NotNull
    @Size(min=3, max=64)
    private String city;

    @NotNull
    @Size(min=6, max=128)
    private String password;

    @NotNull
    @Size(min=6, max=128)
    private String passwordRepeat;

    private Character gender;

    public RegisterBean() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
