package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.TwitterService;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "twitterServiceImpl")
@Transactional
public class TwitterServiceImpl implements TwitterService{

    // Yahoo Where On Earth ID representing the entire world
    private static final long WORLDWIDE_WOE = 1L;

    @Inject
    private Twitter twitter;

    @Override
    public CursoredList<TwitterProfile> getFriends() {
        return twitter.friendOperations().getFriends();
    }

    @Override
    public CursoredList<TwitterProfile> getFollowers() {
        return twitter.friendOperations().getFollowers();
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived() {
        return  twitter.directMessageOperations().getDirectMessagesReceived();
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent() {
        return twitter.directMessageOperations().getDirectMessagesSent();
    }

    @Override
    public DirectMessage sendDirectMessage(String messageTo, String messageText) {
        return twitter.directMessageOperations().sendDirectMessage(messageTo,messageText);
    }

    @Override
    public TwitterProfile getUserProfile() {
        return twitter.userOperations().getUserProfile();
    }

    @Override
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("twitter");
    }

    @Override
    public List<Tweet> getTweets(String search) {
        return twitter.searchOperations().search(search).getTweets();
    }

    @Override
    public List<Tweet> getHomeTimeline() {
        return twitter.timelineOperations().getHomeTimeline();
    }

    @Override
    public List<Tweet> getUserTimeline() {
        return twitter.timelineOperations().getUserTimeline();
    }

    @Override
    public List<Tweet> getMentions() {
        return twitter.timelineOperations().getMentions();
    }

    @Override
    public List<Tweet> getFavorites() {
        return twitter.timelineOperations().getFavorites();
    }

    @Override
    public Tweet updateStatus(String message) {
        return 	twitter.timelineOperations().updateStatus(message);
    }

    @Override
    public Trends getLocalTrends(long var1) {
        return twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE);
    }
}
