package com.yesnault.sag.util;

import java.io.Serializable;

/**
 * Created by Ciprian on 5/1/2015.
 */
public class UsersDTO implements Serializable{

    private String id;

    private String name;

    private String firstName;

    private String lastName;

    private String socialNetType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSocialNetType() {
        return socialNetType;
    }

    public void setSocialNetType(String socialNetType) {
        this.socialNetType = socialNetType;
    }
}
