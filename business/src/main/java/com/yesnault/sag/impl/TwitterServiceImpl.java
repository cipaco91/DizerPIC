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
        if(twitter!=null) {
            return twitter.friendOperations().getFriends();
        }
        return null;
    }

    @Override
    public CursoredList<TwitterProfile> getFollowers() {
        if(twitter!=null) {
            return twitter.friendOperations().getFollowers();
        }
        return null;
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived() {
        if(twitter!=null) {
            return twitter.directMessageOperations().getDirectMessagesReceived();
        }
        return null;
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent() {
        if(twitter!=null) {
            return twitter.directMessageOperations().getDirectMessagesSent();
        }
        return null;
    }

    @Override
    public DirectMessage sendDirectMessage(String messageTo, String messageText) {
        if(twitter!=null) {
            return twitter.directMessageOperations().sendDirectMessage(messageTo, messageText);
        }
        return null;
    }

    @Override
    public TwitterProfile getUserProfile() {
        if(twitter!=null) {
            return twitter.userOperations().getUserProfile();
        }
        return null;
    }

    @Override
    public void simulateExpiredToken() {
        throw new ExpiredAuthorizationException("twitter");
    }

    @Override
    public List<Tweet> getTweets(String search) {
        if(twitter!=null) {
            return twitter.searchOperations().search(search).getTweets();
        }
        return null;
    }

    @Override
    public List<Tweet> getHomeTimeline() {
        if(twitter!=null) {
            return twitter.timelineOperations().getHomeTimeline();
        }
        return null;
    }

    @Override
    public List<Tweet> getUserTimeline() {
        if(twitter!=null) {
            return twitter.timelineOperations().getUserTimeline();
        }
        return null;
    }

    @Override
    public List<Tweet> getMentions() {
        if(twitter!=null) {
            return twitter.timelineOperations().getMentions();
        }
        return null;
    }

    @Override
    public List<Tweet> getFavorites() {
        if(twitter!=null) {
            return twitter.timelineOperations().getFavorites();
        }
        return null;
    }

    @Override
    public Tweet updateStatus(String message) {
        if(twitter!=null) {
            return twitter.timelineOperations().updateStatus(message);
        }
        return null;
    }

    @Override
    public Trends getLocalTrends(long var1) {
        if(twitter!=null) {
            return twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE);
        }
        return null;
    }
}
