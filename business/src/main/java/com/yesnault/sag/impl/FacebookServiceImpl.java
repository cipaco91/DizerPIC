package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "facebookServiceImpl")
@Transactional
public class FacebookServiceImpl implements FacebookService {

    @Inject
    private Facebook facebook;

    @Override
    public List<SNFriend> getFeed() {
        if (facebook != null) {
            PagedList<Post> posts = facebook.feedOperations().getFeed();
            List<SNFriend> snFriends = new ArrayList<>();
            for (Post post : posts) {
                SNFriend snFriend = new SNFriend();
                snFriend.setId(post.getFrom().getId());
                snFriend.setName(post.getMessage());
                snFriend.setProfileImageUrl("http://graph.facebook.com/" + post.getFrom().getId() + "/picture");
                snFriends.add(snFriend);
            }
            return snFriends;
        }
        return new ArrayList<>();
    }

    @Override
    public void postUpdate(String message) {
        facebook.feedOperations().updateStatus(message);
    }

    @Override
    public List<SNFriend> getFriendsFacebook() {
        if (facebook != null) {
            Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBABE4R2rEHPQYFRfvm1wWGtlKZBTrW8fZCua6CKqUVhZBS0riTZAu5YoKLPUTGomuBEsc4Kb3ZB9MZCoA02f0NTLzzStfP5zZCSP7YQb0nYrWm1JiwEs5Qg2rvmq7uZACdcSS1v4dZAq8FuHNb49tugwk4zYnsczSXPn4v3I3csXBOfM7ZAlIdTwe0LQAT81qVhGNzG5V4cYzfDdzUAWnTFR4cZD");
            PagedList<Reference> references = facebook1.friendOperations().getFriends();
            return getSnFriends(references);
        }
        return new ArrayList<>();
    }

    @Override
    public FacebookProfile getUserProfile() {
        if (facebook != null) {
            return facebook.userOperations().getUserProfile();
        }
        return null;
    }

    @Override
    public PagedList<Reference> search(String var1) {
        if (facebook != null) {
            return facebook.userOperations().search(var1);
        }
        return null;
    }

    @Override
    public PagedList<Album> getAlbums() {
        if (facebook != null) {
            return facebook.mediaOperations().getAlbums();
        }
        return null;
    }

    @Override
    public PagedList<Photo> getPhotos(String albumId) {
        if (facebook != null) {
            return facebook.mediaOperations().getPhotos(albumId);
        }
        return null;
    }

    @Override
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("facebook");
    }

    @Override
    public void login() {

    }

    @Override
    public byte[] getProfileImage() {
        byte[] profileImage = facebook.userOperations().getUserProfileImage();
        return profileImage;
    }

    @Override
    public PagedList<Photo> getPhotosProfile() {
        String userId = facebook.userOperations().getUserProfile().getId();
        return facebook.mediaOperations().getPhotos(userId);
    }

    @Override
    public boolean isConnectFacebook() {
        try {
            return facebook.userOperations() != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SNFriend> getCommonFriendsFacebook() {
        List<SNFriend> snFriends = new ArrayList<>();
        Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAI0EwlVQWEmPq1kBONSo2JT4A1aH3xB3wymq8q0ZBaGuBWCHstmjwWRoWZBEUf0ZACxYCXVkYf9ITsZAXDSaRPcAfyRjBHsTtsWfmj80olhU7QTTIx8yT9g5WvUPZA4JnvBkXjtZCPeSuDn4hffBCE04QZBrZCeig7b1EZB39IKzbgiIiLmKqQVkLpHc7IFhGO05vAi4cCQtHhAtGCxE7R6gZD");
        PagedList<Reference> references = facebook1.friendOperations().getFriends();
        facebook1.friendOperations().getMutualFriends(references.get(0).getId());
        return null;
    }

    private List<SNFriend> getSnFriends(List<Reference> references) {
        List<SNFriend> snFriends = new ArrayList<>();
        for (Reference reference : references) {
            SNFriend snFriend = new SNFriend();
            snFriend.setId(reference.getId());
            snFriend.setName(reference.getName());
            snFriend.setProfileImageUrl("http://graph.facebook.com/" + reference.getId() + "/picture");
            snFriend.setProfileURL("http://graph.facebook.com/" + reference.getId());
            snFriend.setSocialNetworkType("facebook");
            snFriends.add(snFriend);
        }
        return snFriends;
    }

    private List<SNFeed> getSnFeeds(PagedList<Post> posts) {
        List<SNFeed> snFeeds = new ArrayList<>();
        for (Post post : posts) {
            SNFeed snFeed = new SNFeed();
            snFeed.setId(post.getId());
            snFeed.setFrom(post.getFrom());
            snFeed.setCreatedTime(post.getCreatedTime());
            snFeed.setUpdatedTime(post.getUpdatedTime());
            snFeed.setTo(post.getTo());
            snFeed.setMessage(post.getMessage());
            snFeed.setPicture(post.getPicture());
            snFeed.setLink(post.getLink());
            snFeed.setName(post.getName());
            snFeed.setCaption(post.getCaption());
            snFeed.setDescription(post.getDescription());
            snFeed.setIcon(post.getIcon());
            snFeed.setApplication(post.getApplication());
            snFeed.setLikes(post.getLikes());
            snFeed.setComments(post.getComments());
            snFeed.setSharesCount(post.getSharesCount());
            snFeed.setStory(post.getStory());
            snFeed.setFeedType(post.getType().name());
            snFeed.setSocialNetworkType("facebook");
        }
        return snFeeds;
    }
}
