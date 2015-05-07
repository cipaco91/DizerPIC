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
    public List<AlbumSN> getAlbums() {
        List<AlbumSN> albumSNs = new ArrayList<>();
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
        List<SNFriend> snFriends = new ArrayList<>();
        Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAGgu2NOF6w6PjgUZBEP6ErOypekG0l1qg4GLEH6fj0PpLuYR718TZCauhZBLQ5ox7jyQlJWvuvl8ciFWkq4r6VfmtDhH6wdDzc7F9OwUci2DKJoAX1NzPCvWcKAy9odVJeaXn0EZCu6lFsYqtmdIWtQ0XmBm8AUnZBASZBHIf8c6sZCEUeTk9jnNNKrjpBGgZAwHycJSOKDbU7fkBgujQmYZD");
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
        BASE64Encoder encoder = new BASE64Encoder();
        List<SNFeed> snFeeds = new ArrayList<>();
        for (Post post : posts) {
            SNFeed snFeed = new SNFeed();
            snFeed.setId(post.getId());
            snFeed.setFrom(post.getFrom());
            snFeed.setCreatedTime(post.getCreatedTime());
            snFeed.setUpdatedTime(post.getUpdatedTime());
            if(post.getCreatedTime()!=null&&post.getUpdatedTime()!=null&&post.getCreatedTime().compareTo(post.getUpdatedTime())<0){
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
            snFeed.setLikesCount(post.getLikes()!=null?post.getLikes().size():0);
            snFeed.setCommentsCount(post.getComments()!=null?post.getComments().size():0);

            if (post.getComments() != null) {
                List<CommentFeed> commentFeeds = new ArrayList<>();
                if( post.getComments().size()>5) {
                    for (Comment comment : post.getComments().subList(0,4)) {
                        CommentFeed commentFeed = new CommentFeed();
                        commentFeed.setComment(comment);
                        commentFeed.setPhotoCommentFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(comment.getFrom().getId())));
                        commentFeeds.add(commentFeed);
                    }
                }else{
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
           if("VIDEO".equals(post.getType().name())) {
               snFeed.setSourceVideo(((VideoPost)post).getSource());
           }

            if("STATUS".equals(post.getType().name())) {
               if(post.getTo()!=null&&post.getMessage()!=null){
                   snFeed.setFeedType("StatusFromTo");
               }else if(post.getMessage()!=null){
                   snFeed.setFeedType("StatusOnlyFrom");
               }else if(post.getStory()!=null){
                   snFeed.setFeedType("StatusStory");
               }
            }

            snFeeds.add(snFeed);
        }
        return snFeeds;
    }
}
