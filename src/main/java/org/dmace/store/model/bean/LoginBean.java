package org.dmace.store.model.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginBean {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 6, max = 128)
    private String password;
    private boolean rememberme = false;

    public LoginBean() {
    }

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{email='" + username + '\'' + "password=********}";
    }
}
