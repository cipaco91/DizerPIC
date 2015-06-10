package com.yesnault.sag.pojo;

import org.springframework.social.facebook.api.Photo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CParaschivescu on 6/10/2015.
 */
public class PhotoSN implements Serializable{

    private Photo photo;

    private List<CommentFeed> commentFeeds;

    private Integer commentsCount;

    private String profileName;

    private String profilePicture;

    private String postText;

    public List<CommentFeed> getCommentFeeds() {
        return commentFeeds;
    }

    public void setCommentFeeds(List<CommentFeed> commentFeeds) {
        this.commentFeeds = commentFeeds;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }
}
