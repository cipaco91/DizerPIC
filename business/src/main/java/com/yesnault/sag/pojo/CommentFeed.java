package com.yesnault.sag.pojo;

import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.Reference;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CParaschivescu on 5/6/2015.
 */
public class CommentFeed implements Serializable{

    private Comment comment;
    private String photoCommentFrom;
    private String commentDate;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getPhotoCommentFrom() {
        return photoCommentFrom;
    }

    public void setPhotoCommentFrom(String photoCommentFrom) {
        this.photoCommentFrom = photoCommentFrom;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
