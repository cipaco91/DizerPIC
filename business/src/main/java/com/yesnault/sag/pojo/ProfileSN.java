package com.yesnault.sag.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CParaschivescu on 4/23/2015.
 */
public class ProfileSN implements Serializable{

    private String id;

    private String fullName;

    private String gender;

    private String email;

    private String birthday;

    private String about;

    private String statusRel;

    private String location;

    private Date dateOfBirthday;

    public ProfileSN(){
    }

    public ProfileSN(String id, String gender, String fullName, String email, String birthday, String about) {
        this.id = id;
        this.gender = gender;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.about = about;
    }

    public ProfileSN(String id, String gender, String fullName, String email, String birthday, String about, String location, String statusRel) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.about = about;
        this.location = location;
        this.statusRel = statusRel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getStatusRel() {
        return statusRel;
    }

    public void setStatusRel(String statusRel) {
        this.statusRel = statusRel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
