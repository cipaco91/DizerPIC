package com.yesnault.sag.pojo;

/**
 * Created by CParaschivescu on 4/9/2015.
 */
public enum FeedType {

    POST("POST"),
    CHECKIN("CHECKIN"),
    LINK("LINK"),
    NOTE("NOTE"),
    PHOTO("PHOTO"),
    STATUS("STATUS"),
    VIDEO("VIDEO"),
    MUSIC ("MUSIC");

    private String code;

    FeedType(String code) {
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
