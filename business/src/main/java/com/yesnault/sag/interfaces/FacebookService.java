package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.*;
import com.yesnault.sag.util.UsersDTO;
import org.springframework.social.facebook.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface FacebookService {

    List<SNFeed> getFeed();

    void postUpdate(String message);

    List<SNFriend> getFriendsFacebook(String name);

    FacebookProfile getUserProfile();

    PagedList<Reference> search(String var1);

    List<AlbumSN> getAlbums(User user);

    PagedList<Photo> getPhotos(String var1);

    void simulateExpiredToken();

    void login();

    byte [] getProfileImage();

    PagedList<Photo> getPhotosProfile(User user);

    List<PhotoSN> imagesPageFacebook(User user);

    boolean isConnectFacebook(User user);

    List<SNFriend> getCommonFriendsFacebook();

    List<PhotoSN> getPhotosFromAlbum(String albumId);

    String addComment(String id, String message);

    void addLike(String id);

    void unlike(String id);

    List<SNFeed> getMyPosts();

    List<CommentFeed> getComments(String objectId);
}
