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

    private String profileAbout;

    private String profileImage;

    private String profileFriend;

    private String profileName;

    private String profileCover;

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

    public String getProfileAbout() {
        return profileAbout;
    }

    public void setProfileAbout(String profileAbout) {
        this.profileAbout = profileAbout;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileFriend() {
        return profileFriend;
    }

    public void setProfileFriend(String profileFriend) {
        this.profileFriend = profileFriend;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileCover() {
        return profileCover;
    }

    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }
}
