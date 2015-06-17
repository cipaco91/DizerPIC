package com.yesnault.sag.model;

import javax.persistence.*;

/**
 * Created by CParaschivescu on 4/15/2015.
 */
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "FK_USER_ID", referencedColumnName = "id")
    private User user;

    @Column(name = "FACEBOOK_FLAG", nullable = false, insertable = true, updatable = true)
    private Boolean facebookFlag;

    @Column(name = "TWITTER_FLAG", nullable = false, insertable = true, updatable = true)
    private Boolean twitterFlag;

    @Column(name = "LINKEDIN_FLAG", nullable = false, insertable = true, updatable = true)
    private Boolean linkedinFlag;

    @Column(name = "GOOGLE_FLAG", nullable = false, insertable = true, updatable = true)
    private Boolean googleFlag;

    @Column(name = "FROM_PROFILE_IMAGE", nullable = false, insertable = true, updatable = true)
    private String fromProfileImage;

    @Column(name = "FROM_PROFILE_ABOUT", nullable = false, insertable = true, updatable = true)
    private String fromProfileAbout;

    @Column(name = "FROM_PROFILE_FRIENDS", nullable = false, insertable = true, updatable = true)
    private String fromProfileFriends;

    @Column(name = "FROM_PROFILE_NAME", nullable = false, insertable = true, updatable = true)
    private String fromProfileName;

    @Column(name = "FROM_PROFILE_COVER", nullable = false, insertable = true, updatable = true)
    private String fromProfileCover;

    @Column(name = "email", insertable = true, updatable = true)
    private String email;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFromProfileImage() {
        return fromProfileImage;
    }

    public void setFromProfileImage(String fromProfileImage) {
        this.fromProfileImage = fromProfileImage;
    }

    public String getFromProfileAbout() {
        return fromProfileAbout;
    }

    public void setFromProfileAbout(String fromProfileAbout) {
        this.fromProfileAbout = fromProfileAbout;
    }

    public String getFromProfileFriends() {
        return fromProfileFriends;
    }

    public void setFromProfileFriends(String fromProfileFriends) {
        this.fromProfileFriends = fromProfileFriends;
    }

    public String getFromProfileName() {
        return fromProfileName;
    }

    public void setFromProfileName(String fromProfileName) {
        this.fromProfileName = fromProfileName;
    }

    public String getFromProfileCover() {
        return fromProfileCover;
    }

    public void setFromProfileCover(String fromProfileCover) {
        this.fromProfileCover = fromProfileCover;
    }

    public Boolean getFacebookFlag() {
        return facebookFlag;
    }

    public void setFacebookFlag(Boolean facebookFlag) {
        this.facebookFlag = facebookFlag;
    }

    public Boolean getTwitterFlag() {
        return twitterFlag;
    }

    public void setTwitterFlag(Boolean twitterFlag) {
        this.twitterFlag = twitterFlag;
    }

    public Boolean getLinkedinFlag() {
        return linkedinFlag;
    }

    public void setLinkedinFlag(Boolean linkedinFlag) {
        this.linkedinFlag = linkedinFlag;
    }

    public Boolean getGoogleFlag() {
        return googleFlag;
    }

    public void setGoogleFlag(Boolean googleFlag) {
        this.googleFlag = googleFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
