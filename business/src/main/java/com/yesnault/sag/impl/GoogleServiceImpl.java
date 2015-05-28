package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
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
