package com.yesnault.sag.pojo;

import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.ListAndCount;
import org.springframework.social.facebook.api.Reference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by CParaschivescu on 4/9/2015.
 */
public class SNFeed implements Serializable{

    private  String id;

    private  Reference from;

    private  Date createdTime;

    private  Date updatedTime;

    private List<Reference> to;

    private String message;

    private String picture;

    private String link;

    private String name;

    private String caption;

    private String description;

    private String icon;

    private Reference application;

    private List<Reference> likes;

    private List<Comment> comments;

    private int sharesCount;

    private String story;

    private String feedType;

    private String socialNetworkType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reference getFrom() {
        return from;
    }

    public void setFrom(Reference from) {
        this.from = from;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<Reference> getTo() {
        return to;
    }

    public void setTo(List<Reference> to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public Reference getApplication() {
        return application;
    }

    public void setApplication(Reference application) {
        this.application = application;
    }

    public List<Reference> getLikes() {
        return likes;
    }

    public void setLikes(List<Reference> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getSocialNetworkType() {
        return socialNetworkType;
    }

    public void setSocialNetworkType(String socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }
}
