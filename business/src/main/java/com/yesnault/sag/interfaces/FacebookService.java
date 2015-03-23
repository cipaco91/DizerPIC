package com.yesnault.sag.interfaces;

import org.springframework.social.facebook.api.*;

import javax.inject.Inject;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface FacebookService {

    PagedList<Post> getFeed();

    void postUpdate(String message);

    PagedList<Reference> getFriendsFacebook();

    FacebookProfile getUserProfile();

    PagedList<Reference> search(String var1);

    PagedList<Album> getAlbums();

    PagedList<Photo> getPhotos(String var1);

    void simulateExpiredToken();

    void login();

}
