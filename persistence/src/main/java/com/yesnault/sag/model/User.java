package com.yesnault.sag.model;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

    @Column(name = "FIRST_NAME", nullable = false, insertable = true, updatable = true)
    private String firstname;

    @Column(name = "LAST_NAME", nullable = false, insertable = true, updatable = true)
    private String lastname;

    @Column(name = "USERNAME", nullable = false, insertable = true, updatable = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true)
    private String password;

    @Column(name = "ACTIVE", nullable = false, insertable = true, updatable = true)
    private boolean active;

    @Column(name = "PROFILE_CONFIGURE", nullable = false, insertable = true, updatable = true)
    private boolean profileConf;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="user")
    private UserProfile userProfile;

    @Column(name = "LOGIN_ACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean loginActive;

    public User(String firstname, String lastname) {

        Assert.hasText(firstname);
        Assert.hasText(lastname);

        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User() {
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstname='%s', lastname='%s']",
                getId(), firstname, lastname);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isProfileConf() {
        return profileConf;
    }

    public void setProfileConf(boolean profileConf) {
        this.profileConf = profileConf;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Boolean getLoginActive() {
        return loginActive;
    }

    public void setLoginActive(Boolean loginActive) {
        this.loginActive = loginActive;
    }
}
