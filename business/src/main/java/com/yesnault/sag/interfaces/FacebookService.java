package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.AlbumSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.util.UsersDTO;
import org.springframework.social.facebook.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface FacebookService {

    List<SNFeed>getFeed();

    void postUpdate(String message);

    List<SNFriend> getFriendsFacebook();

    FacebookProfile getUserProfile();

    PagedList<Reference> search(String var1);

    List<AlbumSN> getAlbums();

    PagedList<Photo> getPhotos(String var1);

    void simulateExpiredToken();

    void login();

    byte [] getProfileImage();

    PagedList<Photo> getPhotosProfile();

    boolean isConnectFacebook(User user);

    List<SNFriend> getCommonFriendsFacebook();

    List<Photo> getPhotosFromAlbum(String albumId);

    String addComment(String id, String message);

    void addLike(String id);

    void unlike(String id);
}
