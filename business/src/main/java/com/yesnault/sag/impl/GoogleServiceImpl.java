package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.CommentFeed;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */
@Service(value = "googleServiceImpl")
@Transactional
public class GoogleServiceImpl implements GoogleService {

    @Inject
    private Google google;

    @Inject
    private UserService userService;

    @Override
    public boolean isConnectGoogle(User user) {
        try {
            List<User> listUsers = userService.findByUsername(user.getUsername());
            if(listUsers!=null&&listUsers.size()>0) {
                UserProfile userProfile=listUsers.get(0).getUserProfile();
                if (new Boolean(true).equals(userProfile.getGoogleFlag())) {
                    return google.plusOperations() != null;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SNFriend> findFriends() {
        PeoplePage peoplePage = google.plusOperations().getPeopleInCircles(google.plusOperations().getGoogleProfile().getId(), null);
        List<Person> persons = peoplePage.getItems();
        return getSnFriends(persons);
    }

    @Override
    public List<SNFeed> findFeeds() {
        List<SNFeed> snFeeds=new ArrayList<SNFeed>();
        ActivitiesPage activitiesPage=google.plusOperations().searchPublicActivities("home", null);
        List<Activity> activities = activitiesPage.getItems();
        int i=0;
        while (activities != null) {
            i++;
            snFeeds.addAll(getSNFeeds(activities));
            if (activitiesPage.getNextPageToken() == null || i==3) {
                break;
            }
            activitiesPage=google.plusOperations().searchPublicActivities("home", activitiesPage.getNextPageToken());
            activities = activitiesPage.getItems();
        }

        return snFeeds;
    }

    private List<SNFeed> getSNFeeds(List<Activity> activities){
        List<SNFeed> snFeeds=new ArrayList<SNFeed>();
        for(Activity activity:activities){
            SNFeed snFeed=new SNFeed();
            snFeed.setSocialNetworkType("google");
            snFeed.setFrom(new Reference(activity.getActor().getDisplayName(),activity.getActor().getDisplayName()));
            snFeed.setPhotoFrom(activity.getActor().getImageUrl());
            snFeed.setUpdatedTime(activity.getUpdated());
            if(activity.getAttachments()!=null&&activity.getAttachments().size()>0){
                Activity.Attachment attachment=activity.getAttachments().get(0);
                if(attachment instanceof Activity.Article){
                    snFeed.setFeedType("articleGoogle");
                    snFeed.setMessage(attachment.getDisplayName());
                }else if(attachment instanceof Activity.Album){
                    snFeed.setFeedType("album");
                }else if(attachment instanceof Activity.Event){
                    snFeed.setFeedType("event");
                }else if(attachment instanceof Activity.Photo){
                    snFeed.setPicture(attachment.getPreviewImageUrl());
                    snFeed.setMessage(activity.getTitle());
                    snFeed.setFeedType("photoGoogle");
                }else if(attachment instanceof Activity.Video){
                    snFeed.setFeedType("videoGoogle");
                    snFeed.setSrc(((Activity.Video)attachment).getUrl());
                    snFeed.setMessage(activity.getTitle());
                }else if(attachment instanceof Activity.Audio){
                    snFeed.setFeedType("audio");
                }
            }
            snFeed.setLikesCount(activity.getPlusOners());

            List<ActivityComment> activityComments=google.plusOperations().getComments(activity.getId(), null).getItems();
            if(activityComments!=null&&activityComments.size()>0) {
                List<CommentFeed> commentFeeds = new ArrayList<CommentFeed>();
                for (ActivityComment activityComment : activityComments) {
                    CommentFeed commentFeed = new CommentFeed();
                    commentFeed.setPhotoCommentFrom(activityComment.getActor().getImageUrl());
                    commentFeed.setComment(new Comment(activityComment.getActor().getId(),
                            new Reference(activityComment.getActor().getId(), activityComment.getActor().getDisplayName()), activityComment.getContent(),
                            activityComment.getPublished()));
                }
                snFeed.setCommentsFeeds(commentFeeds);
                snFeed.setCommentsCount(commentFeeds.size());
            }

            snFeeds.add(snFeed);
        }
        return snFeeds;
    }

    private List<SNFriend> getSnFriends(List<Person> persons) {

        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        for (Person person : persons) {
            SNFriend snFriend = new SNFriend();
            snFriend.setId(person.getId());
            snFriend.setName(person.getDisplayName());
            snFriend.setProfileImageUrl(person.getImageUrl());
            snFriend.setProfileURL(person.getUrl());
            snFriend.setSocialNetworkType("google");
            snFriend.setSocialNetworkTypePicture("images/googlePlus.jpg");
            snFriends.add(snFriend);
        }
        return snFriends;
    }
}
