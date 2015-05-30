package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Activity;
import org.springframework.social.google.api.plus.PeoplePage;
import org.springframework.social.google.api.plus.Person;
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

    @Override
    public boolean isConnectGoogle() {
        try {
            return google.plusOperations() != null;
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
        List<Activity> activities = google.plusOperations().searchPublicActivities("home", null).getItems();
        for(Activity activity:activities){
            SNFeed snFeed=new SNFeed();
            snFeed.setSocialNetworkType("google");
            snFeed.setFrom(new Reference(activity.getActor().getDisplayName(),activity.getActor().getDisplayName()));
            snFeed.setPhotoFrom(activity.getActor().getImageUrl());
            snFeed.setUpdatedTime(activity.getUpdated());
            if(activity.getAttachments()!=null){
                Activity.Attachment attachment=activity.getAttachments().get(0);
                if(attachment instanceof Activity.Article){
                    snFeed.setFeedType("article");
                }else if(attachment instanceof Activity.Album){
                    snFeed.setFeedType("album");
                }else if(attachment instanceof Activity.Event){
                    snFeed.setFeedType("event");
                }else if(attachment instanceof Activity.Photo){
                    snFeed.setPicture(attachment.getPreviewImageUrl());
                    snFeed.setMessage(attachment.getContent());
                    snFeed.setFeedType("photoGoogle");
                }else if(attachment instanceof Activity.Video){
                    snFeed.setFeedType("videoGoogle");
                    snFeed.setSrc(((Activity.Video)attachment).getUrl());
                    snFeed.setMessage(attachment.getContent());
                }else if(attachment instanceof Activity.Audio){
                    snFeed.setFeedType("audio");
                }
            }

        }
        return null;
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
