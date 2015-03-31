package com.yesnault.sag.pojo;

import java.io.Serializable;

/**
 * Created by CParaschivescu on 3/31/2015.
 */
public class LinkedinFeed implements Serializable{

    private String profileImageUrl;

    private String text;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
