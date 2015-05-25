package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.pojo.AlbumSN;
import com.yesnault.sag.pojo.CommentFeed;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
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
    public List<SNFeed> getFeed() {
        if (facebook != null) {
            PagingParameters pagingParameters = new PagingParameters(7, 0, null, null);
            PagedList<Post> posts = facebook.feedOperations().getHomeFeed(pagingParameters);
            return getSnFeeds(posts);
        }
        return new ArrayList<SNFeed>();
    }

    @Override
    public void postUpdate(String message) {
        facebook.feedOperations().updateStatus(message);
    }

    @Override
    public List<SNFriend> getFriendsFacebook() {
        if (facebook != null) {
//            Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAFKG3EeBkK5KjUZAa9fSAYZAkvZCf6hZBT0MyyclZBIZCQEuvbHnaWqledZCwiaJ3kZCJZAO7jeIfFFSIDwA8fqBif5Sk9KulZCO3WOhj2zsaOYFWW3IUXPolnj7qA67ZCetWLD7iRw9fgxecdFuoxkFlseXw7rGrIDkidfex0eKt6l2KxGPRMsb6wNGJVmMwSjUbzMwB9aqA4YiiS0omkXwQ8ZD");
            PagedList<Reference> references = facebook.friendOperations().getFriends();
            return getSnFriends(references);
        }
        return new ArrayList<SNFriend>();
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
    public List<AlbumSN> getAlbums() {
        List<AlbumSN> albumSNs = new ArrayList<AlbumSN>();
        if (facebook != null) {
            List<Album> albums = facebook.mediaOperations().getAlbums();
            for (Album album : albums) {
                AlbumSN albumSN = new AlbumSN();
                albumSN.setAlbum(album);
                if (album.getCoverPhotoId() != null) {
                    Photo photoCover = facebook.mediaOperations().getPhoto(album.getCoverPhotoId());
                    if (photoCover != null) {
                        albumSN.setPhotoCover(photoCover);
                    }
                    albumSNs.add(albumSN);
                }
            }
        }
        return albumSNs;
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
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAFKG3EeBkK5KjUZAa9fSAYZAkvZCf6hZBT0MyyclZBIZCQEuvbHnaWqledZCwiaJ3kZCJZAO7jeIfFFSIDwA8fqBif5Sk9KulZCO3WOhj2zsaOYFWW3IUXPolnj7qA67ZCetWLD7iRw9fgxecdFuoxkFlseXw7rGrIDkidfex0eKt6l2KxGPRMsb6wNGJVmMwSjUbzMwB9aqA4YiiS0omkXwQ8ZD");

        PagedList<Reference> references = facebook1.friendOperations().getFriends();
        facebook1.friendOperations().getMutualFriends(references.get(0).getId());
        return null;
    }

    @Override
    public List<Photo> getPhotosFromAlbum(String albumId) {
        return new ArrayList<Photo>(facebook.mediaOperations().getPhotos(albumId));
    }

    @Override
    public String addComment(String id, String message) {
        return facebook.commentOperations().addComment(id,message);
    }

    @Override
    public void addLike(String id) {
        facebook.likeOperations().like(id);
    }

    private List<SNFriend> getSnFriends(List<Reference> references) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        for (Reference reference : references) {
            SNFriend snFriend = new SNFriend();
            snFriend.setId(reference.getId());
            snFriend.setName(reference.getName());
            snFriend.setProfileImageUrl("http://graph.facebook.com/" + reference.getId() + "/picture");
            snFriend.setProfileURL("http://graph.facebook.com/" + reference.getId());
            snFriend.setSocialNetworkType("facebook");
            snFriend.setSocialNetworkTypePicture("images/social/Facebook-logo.jpg");
            snFriends.add(snFriend);
        }
        return snFriends;
    }

    private List<SNFeed> getSnFeeds(PagedList<Post> posts) {
        BASE64Encoder encoder = new BASE64Encoder();
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        for (Post post : posts) {
            if("VIDEO".equals(post.getType().name())||"PHOTO".equals(post.getType().name())||"StatusOnlyFrom".equals(post.getType().name())) {
                SNFeed snFeed = new SNFeed();
                snFeed.setId(post.getId());
                snFeed.setFrom(post.getFrom());
                snFeed.setCreatedTime(post.getCreatedTime());
                snFeed.setUpdatedTime(post.getUpdatedTime());
                if (post.getCreatedTime() != null && post.getUpdatedTime() != null && post.getCreatedTime().compareTo(post.getUpdatedTime()) < 0) {
                    snFeed.setCreatedTime(post.getUpdatedTime());
                }
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
                snFeed.setLikesCount(post.getLikes() != null ? post.getLikes().size() : 0);
                snFeed.setCommentsCount(post.getComments() != null ? post.getComments().size() : 0);

                if (post.getComments() != null) {
                    List<CommentFeed> commentFeeds = new ArrayList<CommentFeed>();
                    if (post.getComments().size() > 5) {
                        for (Comment comment : post.getComments().subList(0, 4)) {
                            CommentFeed commentFeed = new CommentFeed();
                            commentFeed.setComment(comment);
                            commentFeed.setPhotoCommentFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(comment.getFrom().getId())));
                            commentFeeds.add(commentFeed);
                        }
                    } else {
                        for (Comment comment : post.getComments()) {
                            CommentFeed commentFeed = new CommentFeed();
                            commentFeed.setComment(comment);
                            commentFeed.setPhotoCommentFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(comment.getFrom().getId())));
                            commentFeeds.add(commentFeed);
                        }
                    }
                    snFeed.setCommentsFeeds(commentFeeds);
                }
                snFeed.setSharesCount(post.getSharesCount());
                snFeed.setStory(post.getStory());
                snFeed.setFeedType(post.getType().name());
                snFeed.setSocialNetworkType("facebook");
                snFeed.setPhotoFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(post.getFrom().getId())));
                if ("VIDEO".equals(post.getType().name())) {
                    snFeed.setSrc(((VideoPost) post).getSource());
                }

                if ("STATUS".equals(post.getType().name())) {
                    if (post.getTo() != null && post.getMessage() != null) {
                        snFeed.setFeedType("StatusFromTo");
                    } else if (post.getMessage() != null) {
                        snFeed.setFeedType("StatusOnlyFrom");
                    } else if (post.getStory() != null) {
                        snFeed.setFeedType("StatusStory");
                    }
                }
                snFeeds.add(snFeed);
            }
        }
        return snFeeds;
    }
}
