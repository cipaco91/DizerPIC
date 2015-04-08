package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.facebook.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface FacebookService {

    List<SNFriend>getFeed();

    void postUpdate(String message);

    List<SNFriend> getFriendsFacebook();

    FacebookProfile getUserProfile();

    PagedList<Reference> search(String var1);

    PagedList<Album> getAlbums();

    PagedList<Photo> getPhotos(String var1);

    void simulateExpiredToken();

    void login();

    byte [] getProfileImage();

    PagedList<Photo> getPhotosProfile();

    boolean isConnectFacebook();


}
