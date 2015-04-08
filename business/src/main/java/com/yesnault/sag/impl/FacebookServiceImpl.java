package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
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
public class FacebookServiceImpl implements FacebookService{

    @Inject
    private Facebook facebook;

    @Override
    public  List<SNFriend> getFeed() {
        if(facebook!=null) {
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
    public  List<SNFriend> getFriendsFacebook() {
        if(facebook!=null) {
            Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAOHm3ZCRaqtHiF5LYoPU5VSphob5YS09q2moZBdAZA3LBnHsxjrbV7opTFYrcZBAIZBR8ksiwMLbBOlEWsZAwCgwt30c9UPwCrFR9BhtPAtThu9vm4KGNPV1rIbVfQziYwIRU1SOPR9fqQc8PksPuAyWt4UGng11yqMDCAYZBy534sqwtjvtEHmK13skZAII67s1skaRD18lPunP3XpITZAUZD");
            PagedList<Reference> references = facebook1.friendOperations().getFriends();
            return  getSnFriends(references);
        }
        return new ArrayList<>();
    }

    @Override
    public FacebookProfile getUserProfile() {
        if(facebook!=null) {
            return facebook.userOperations().getUserProfile();
        }
        return null;
    }

    @Override
    public PagedList<Reference> search(String var1) {
        if(facebook!=null) {
            return facebook.userOperations().search(var1);
        }
        return null;
    }

    @Override
    public PagedList<Album> getAlbums() {
        if(facebook!=null) {
            return facebook.mediaOperations().getAlbums();
        }
        return null;
    }

    @Override
    public PagedList<Photo> getPhotos(String albumId) {
        if(facebook!=null) {
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
    public byte [] getProfileImage() {
        byte [] profileImage=facebook.userOperations().getUserProfileImage();
        return profileImage;
    }

    @Override
    public PagedList<Photo> getPhotosProfile() {
        String userId=facebook.userOperations().getUserProfile().getId();
        return facebook.mediaOperations().getPhotos(userId);
    }

    @Override
    public boolean isConnectFacebook() {
        try{
            return facebook.userOperations()!=null;
        }catch (Exception e){
            return false;
        }
    }

    private List<SNFriend> getSnFriends(List<Reference> references){
        List<SNFriend> snFriends = new ArrayList<>();
        for (Reference reference : references) {
            SNFriend snFriend = new SNFriend();
            snFriend.setId(reference.getId());
            snFriend.setName(reference.getName());
            snFriend.setProfileImageUrl("http://graph.facebook.com/" + reference.getId() + "/picture");
            snFriend.setProfileURL("http://graph.facebook.com/" + reference.getId());
            snFriends.add(snFriend);
        }
        return snFriends;
    }
}
