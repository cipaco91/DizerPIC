package com.yesnault.sag.pojo;

import java.io.Serializable;

/**
 * Created by CParaschivescu on 3/31/2015.
 */
public class FacebookFriend implements Serializable{

    private String id;

    private String profileImageUrl;

    private String name;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
