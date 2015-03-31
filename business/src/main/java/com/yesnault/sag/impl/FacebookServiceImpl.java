package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.pojo.FacebookFriend;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
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
    public  List<FacebookFriend> getFeed() {
        PagedList<Post> posts=facebook.feedOperations().getFeed();
        List<FacebookFriend> facebookFriends=new ArrayList<>();
        for(Post post:posts){
            FacebookFriend facebookFriend=new FacebookFriend();
            facebookFriend.setId(post.getFrom().getId());
            facebookFriend.setName(post.getMessage());
            facebookFriend.setProfileImageUrl("http://graph.facebook.com/" + post.getFrom().getId()+ "/picture");
            facebookFriends.add(facebookFriend);
        }
        return facebookFriends;
    }

    @Override
    public void postUpdate(String message) {
        facebook.feedOperations().updateStatus(message);
    }

    @Override
    public  List<FacebookFriend> getFriendsFacebook() {
        Facebook facebook1=new FacebookTemplate("CAACEdEose0cBAARP8lPP75zx7oQmf8FktWWoUK10wuUN0goDZCD2tn0GnadO6ZCGaa9kiwvmht6sIRZCZAOXKcXmAYraTluXhCaRqSvpww2jdkLIKAmwt1ph5ZCpfMHqFk04MiZCqIbEa9fF5NL8EagLwdhdYhx73nCage1aZAF3GYZCwQw1ZBN9LmHphn4eZBrU8yHCeMfc8q033mIOiPA5XYKuc6gzH87jgZD");
        PagedList<Reference> references=facebook1.friendOperations().getFriends();
        List<FacebookFriend> facebookFriends=new ArrayList<>();
        for(Reference reference:references){
            FacebookFriend facebookFriend=new FacebookFriend();
            facebookFriend.setId(reference.getId());
            facebookFriend.setName(reference.getName());
            facebookFriend.setProfileImageUrl("http://graph.facebook.com/" + reference.getId()+ "/picture");
            facebookFriends.add(facebookFriend);
        }
        return facebookFriends;
    }

    @Override
    public FacebookProfile getUserProfile() {
        return facebook.userOperations().getUserProfile();
    }

    @Override
    public PagedList<Reference> search(String var1) {
        return facebook.userOperations().search(var1);
    }

    @Override
    public PagedList<Album> getAlbums() {
        return facebook.mediaOperations().getAlbums();
    }

    @Override
    public PagedList<Photo> getPhotos(String albumId) {
        return facebook.mediaOperations().getPhotos(albumId);
    }

    @Override
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("facebook");
    }

    @Override
    public void login() {

    }
}
