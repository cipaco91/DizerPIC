package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.FacebookFriend;
import org.springframework.social.facebook.api.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface FacebookService {

    List<FacebookFriend>getFeed();

    void postUpdate(String message);

    List<FacebookFriend> getFriendsFacebook();

    FacebookProfile getUserProfile();

    PagedList<Reference> search(String var1);

    PagedList<Album> getAlbums();

    PagedList<Photo> getPhotos(String var1);

    void simulateExpiredToken();

    void login();

    byte [] getProfileImage();

}
