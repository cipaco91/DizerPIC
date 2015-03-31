package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "facebookServiceImpl")
@Transactional
public class FacebookServiceImpl implements FacebookService{

    @Inject
    private Facebook facebook;

    @Override
    public PagedList<Post> getFeed() {
        return facebook.feedOperations().getFeed();
    }

    @Override
    public void postUpdate(String message) {
        facebook.feedOperations().updateStatus(message);
    }

    @Override
    public PagedList<Reference> getFriendsFacebook() {
        Facebook facebook1=new FacebookTemplate("CAACEdEose0cBAAXRznDfpTRqs9X6zp5FAGswd83ZBSvDVJiemsdXs8aujedpVwcWyo18g7aUIJfwmEdOouAcFXE8dhkuPSs7YgLtNZAPOsda4sn8Rn38IfNZCRCI0wQycpIehlNi393ZBYs4Dg3D19htHVyleZCt7Rk3bidDGz2kb35eyyiC1AZAP3IkbJlZCuEQhikzyB2ehfG73mTqO2r9mJA6AQZAbkcZD");
        return facebook1.friendOperations().getFriends();
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
