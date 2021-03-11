package com.example.rrszoo.Java;

import java.security.acl.LastOwnerException;

public class Login {

    private String login;
    private String pas;
    private String admin;
    private String email;

    public Login(String login, String pas, String admin, String email) {
        this.login = login;
        this.pas = pas;
        this.admin = admin;
        this.email = email;
    }

    public Login(String login, String pas) {
        this.login = login;
        this.pas = pas;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

