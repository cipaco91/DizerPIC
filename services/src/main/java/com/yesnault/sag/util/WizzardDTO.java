package com.yesnault.sag.util;

import java.io.Serializable;

/**
 * Created by CParaschivescu on 4/27/2015.
 */
public class WizzardDTO implements Serializable{

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isFacebook;

    private Boolean isTwitter;

    private Boolean isLinkedin;

    private String username;

    private String password;

    private String repassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsLinkedin() {
        return isLinkedin;
    }

    public void setIsLinkedin(Boolean isLinkedin) {
        this.isLinkedin = isLinkedin;
    }

    public Boolean getIsTwitter() {
        return isTwitter;
    }

    public void setIsTwitter(Boolean isTwitter) {
        this.isTwitter = isTwitter;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsFacebook() {
        return isFacebook;
    }

    public void setIsFacebook(Boolean isFacebook) {
        this.isFacebook = isFacebook;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
