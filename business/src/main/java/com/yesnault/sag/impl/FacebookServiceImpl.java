package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.interfaces.UtilService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.*;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.linkedin.api.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "facebookServiceImpl")
@Transactional
public class FacebookServiceImpl implements FacebookService {

    @Inject
    private Facebook facebook;

    @Inject
    private UserService userService;

    @Inject
    private UtilService utilService;

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
    public List<SNFriend> getFriendsFacebook(String name) {
        if (facebook != null) {
            Facebook facebook1 = new FacebookTemplate("CAACEdEose0cBAMLsiEJaFnjSKCuZA4uR6C9PfLzZAook72QaTg5yOuq1UoMJIJRTJSKYoKmup6OZCevZC5kZC48O8KlGGwOo6Ho7xTSamdZChcsjG4A5pXkozIloW01t7pGfNSEmMRc3gTIhbaCXEcIumKiXrM89wufpirIqbJFlcqedlAabHMt241YlaLzqxcvxS6pq8MKivFhBWxGClqlfGr4spzlPIZD");
            PagedList<Reference> references = facebook1.friendOperations().getFriends();
            return getSnFriends(references, name);
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
    public boolean isConnectFacebook(User user) {
        try {

            List<User> listUsers = userService.findByUsername(user.getUsername());
            if (listUsers != null && listUsers.size() > 0) {
                UserProfile userProfile = listUsers.get(0).getUserProfile();
                if (new Boolean(true).equals(userProfile.getFacebookFlag())) {
                    return facebook.userOperations() != null;
                }
            }

            if (new Boolean(true).equals(user.getUserProfile().getFacebookFlag())) {
                return facebook.userOperations() != null;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SNFriend> getCommonFriendsFacebook() {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        Facebook facebook1 = new FacebookTemplate("CAAIZBUVtow3IBAMFmEPY5WvG4Y5AUwmNb6JMYJ8xKACZC6YsqWYt86ZBtsllZAWZAO38RMetpgpaH7UwI1nnni1jdET409ZCbbh8LygUZCQIqPqE3zndZBrgpa3dut7bW4JCIuGQlYTap6vX834urnZAYfSUH88EHFGntnOj15eSTegaGifkWngRg");

        PagedList<Reference> references = facebook1.friendOperations().getFriends();
        facebook1.friendOperations().getMutualFriends(references.get(0).getId());
        return null;
    }

    @Override
    public List<PhotoSN> getPhotosFromAlbum(String albumId) {
        List<Photo> photos = new ArrayList<Photo>(facebook.mediaOperations().getPhotos(albumId));
        List<PhotoSN> photoSNs=new ArrayList<>();
        String imageProfile="http://graph.facebook.com/" + facebook.userOperations().getUserProfile().getId()+ "/picture";
        for(Photo photo:photos){
            PhotoSN photoSN=new PhotoSN();
            photoSN.setPhoto(photo);
            List<CommentFeed> commentFeeds=getComments(photo.getId());
            photoSN.setCommentFeeds(commentFeeds);
            photoSN.setProfileName(photo.getFrom().getName());
            photoSN.setProfilePicture(imageProfile);
            photoSNs.add(photoSN);
        }
        return photoSNs;
    }

    @Override
    public String addComment(String id, String message) {
        return facebook.commentOperations().addComment(id, message);
    }

    @Override
    public void addLike(String id) {
        facebook.likeOperations().like(id);
    }

    @Override
    public void unlike(String id) {
        facebook.likeOperations().unlike(id);
    }

    @Override
    public List<SNFeed> getMyPosts() {
        if (facebook != null) {
            PagingParameters pagingParameters = new PagingParameters(10, 0, null, null);
            PagedList<Post> posts = facebook.feedOperations().getFeed(pagingParameters);
            return getSnFeeds(posts);
        }
        return new ArrayList<SNFeed>();
    }

    @Override
    public List<CommentFeed> getComments(String objectId) {
        PagedList<Comment> commentsPage=facebook.commentOperations().getComments(objectId);
        List<CommentFeed> commentFeeds=new ArrayList<>();
        for(Comment comment:commentsPage){
            CommentFeed commentFeed=new CommentFeed();
            commentFeed.setComment(comment);
            commentFeed.setCommentDate(comment.getCreatedTime().toString());
            commentFeed.setPhotoCommentFrom("http://graph.facebook.com/" + comment.getFrom().getId() + "/picture");
            commentFeed.setCreatedTime(comment.getCreatedTime());
            commentFeeds.add(commentFeed);
        }
        return commentFeeds;
    }

    private List<SNFriend> getSnFriends(List<Reference> references, String name) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        for (Reference reference : references) {
            if (name == null || reference.getName().contains(name)) {
                SNFriend snFriend = new SNFriend();
                snFriend.setId(reference.getId());
                snFriend.setName(reference.getName());
                snFriend.setProfileImageUrl("http://graph.facebook.com/" + reference.getId() + "/picture");
                snFriend.setProfileURL("http://graph.facebook.com/" + reference.getId());
                snFriend.setSocialNetworkType("facebook");
                snFriend.setSocialNetworkTypePicture("images/social/Facebook-logo.jpg");
                snFriends.add(snFriend);
            }
        }
        return snFriends;
    }

    private List<SNFeed> getSnFeeds(PagedList<Post> posts) {
        BASE64Encoder encoder = new BASE64Encoder();
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        for (Post post : posts) {
            if ("VIDEO".equals(post.getType().name()) || "PHOTO".equals(post.getType().name()) || "StatusOnlyFrom".equals(post.getType().name())) {
                SNFeed snFeed = new SNFeed();
                snFeed.setId(post.getId());
                snFeed.setFrom(post.getFrom());
                snFeed.setCreatedTime(post.getCreatedTime());
                snFeed.setUpdatedTime(post.getUpdatedTime());
                if (post.getCreatedTime() != null && post.getUpdatedTime() != null && post.getCreatedTime().compareTo(post.getUpdatedTime()) < 0) {
                    snFeed.setCreatedTime(post.getUpdatedTime());
                }
                snFeed.setTo(post.getTo());
                snFeed.setMessage(post.getMessage() != null ? post.getMessage() : post.getStory() != null ? post.getStory() :
                        post.getDescription() != null ? post.getDescription() : "");

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
                            commentFeed.setCreatedTime(comment.getCreatedTime());
                            commentFeed.setPhotoCommentFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(comment.getFrom().getId())));
                            long timeDiff = Math.abs(new Date().getTime() - comment.getCreatedTime().getTime());
                            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff));

                            if (minutes < 60) {
                                commentFeed.setCommentDate(minutes + " minutes ago");
                            } else if (minutes > 60) {
                                long hours = minutes / 60;
                                if (hours == 1) {
                                    commentFeed.setCommentDate(hours + " hour ago ");
                                } else {
                                    if (hours > 24) {
                                        long days = hours / 24;
                                        commentFeed.setCommentDate(days + " days ago ");
                                    } else {
                                        commentFeed.setCommentDate(hours + " hours ago ");
                                    }

                                }
                            }

                            commentFeeds.add(commentFeed);
                        }
                    } else {
                        for (Comment comment : post.getComments()) {
                            CommentFeed commentFeed = new CommentFeed();
                            commentFeed.setComment(comment);
                            commentFeed.setCreatedTime(comment.getCreatedTime());
                            commentFeed.setPhotoCommentFrom("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(comment.getFrom().getId())));
                            long timeDiff = Math.abs(new Date().getTime() - comment.getCreatedTime().getTime());
                            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff));

                            if (minutes < 60) {
                                commentFeed.setCommentDate(minutes + " minutes ago");
                            } else if (minutes > 60) {
                                long hours = minutes / 60;
                                if (hours == 1) {
                                    commentFeed.setCommentDate(hours + " hour ago ");
                                } else {
                                    if (hours > 24) {
                                        long days = hours / 24;
                                        commentFeed.setCommentDate(days + " days ago ");
                                    } else {
                                        commentFeed.setCommentDate(hours + " hours ago ");
                                    }

                                }
                            }

                            commentFeeds.add(commentFeed);
                        }
                    }
                    Collections.sort(commentFeeds);
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

                if ("PHOTO".equals(post.getType().name())) {
                    snFeed.setPicture(facebook.mediaOperations().getPhoto(((PhotoPost) post).getPhotoId()).getSource());
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
