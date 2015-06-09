package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.TwitterService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.interfaces.UtilService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.*;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "twitterServiceImpl")
@Transactional
public class TwitterServiceImpl implements TwitterService {

    // Yahoo Where On Earth ID representing the entire world
    private static final long WORLDWIDE_WOE = 1L;

    @Inject
    private Twitter twitter;

    @Inject
    private UserService userService;

    @Inject
    private UtilService utilService;

    @Override
    public List<SNFriend> getFriends(String name) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if (twitter != null) {
            CursoredList<TwitterProfile> twitterProfiles = twitter.friendOperations().getFriends();
            twitter.timelineOperations().getRetweetsOfMe();
            return getSnFriends(twitterProfiles,name);
        }
        return snFriends;
    }

    @Override
    public List<SNFriend> getFollowers(String name) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if (twitter != null) {
            CursoredList<TwitterProfile> twitterProfiles = twitter.friendOperations().getFollowers();
            return getSnFriends(twitterProfiles,name);
        }
        return snFriends;
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived() {
        if (twitter != null) {
            return twitter.directMessageOperations().getDirectMessagesReceived();
        }
        return null;
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent() {
        if (twitter != null) {
            return twitter.directMessageOperations().getDirectMessagesSent();
        }
        return null;
    }

    @Override
    public DirectMessage sendDirectMessage(String messageTo, String messageText) {
        if (twitter != null) {
            return twitter.directMessageOperations().sendDirectMessage(messageTo, messageText);
        }
        return null;
    }

    @Override
    public TwitterProfile getUserProfile() {
        if (twitter != null) {
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
        if (twitter != null) {
            return twitter.searchOperations().search(search).getTweets();
        }
        return null;
    }

    @Override
    public List<Tweet> getHomeTimeline() {
        if (twitter != null) {
            return twitter.timelineOperations().getHomeTimeline();
        }
        return null;
    }

    @Override
    public List<Tweet> getUserTimeline() {
        if (twitter != null) {
            return twitter.timelineOperations().getUserTimeline();
        }
        return null;
    }

    @Override
    public List<Tweet> getMentions() {
        if (twitter != null) {
            return twitter.timelineOperations().getMentions();
        }
        return null;
    }

    @Override
    public List<Tweet> getFavorites() {
        if (twitter != null) {
            return twitter.timelineOperations().getFavorites();
        }
        return null;
    }

    @Override
    public Tweet updateStatus(String message) {
        if (twitter != null) {
            return twitter.timelineOperations().updateStatus(message);
        }
        return null;
    }

    @Override
    public Trends getLocalTrends(long var1) {
        if (twitter != null) {
            return twitter.searchOperations().getLocalTrends(WORLDWIDE_WOE);
        }
        return null;
    }

    @Override
    public boolean isConnectTwitter(User user) {
        try {

            List<User> listUsers = userService.findByUsername(user.getUsername());
            if(listUsers!=null&&listUsers.size()>0) {
                UserProfile userProfile=listUsers.get(0).getUserProfile();
                if (new Boolean(true).equals(userProfile.getTwitterFlag())) {
                    return twitter.userOperations() != null;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SNFriend> getSnFriends(CursoredList<TwitterProfile> twitterProfiles,String name) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        for (TwitterProfile twitterProfile : twitterProfiles) {
            if(name == null || twitterProfile.getScreenName().contains(name) ) {
                SNFriend snFriend = new SNFriend();
                snFriend.setId(Long.toString(twitterProfile.getId()));
                snFriend.setName(twitterProfile.getScreenName());
                snFriend.setProfileImageUrl(twitterProfile.getProfileImageUrl());
                snFriend.setProfileURL(twitterProfile.getProfileUrl());
                snFriend.setSocialNetworkType("twitter");
                snFriend.setSocialNetworkTypePicture("images/logo_twitter.png");
                snFriends.add(snFriend);
            }
        }
        return snFriends;
    }

    @Override
    public List<SNFeed> getFeed() {
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        return getSnFeeds(tweets);
    }

    @Override
    public List<SNFeed> getMyPosts() {
        List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(twitter.userOperations().getProfileId());
        return getSnFeeds(tweets);
    }

    @Override
    public SNFeed retweet(long tweetId) {
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(twitter.timelineOperations().retweet(tweetId));
        return getSnFeeds(tweets).get(0);
    }

    @Override
    public void addToFavorites(long tweetId) {
        twitter.timelineOperations().addToFavorites(tweetId);
    }

    @Override
    public List<SNFeed> favoritesTweets() {
        List<Tweet> tweets=twitter.timelineOperations().getFavorites();
        return getSnFeeds(tweets);
    }

    private List<SNFeed> getSnFeeds(List<Tweet> tweets) {
        BASE64Encoder encoder = new BASE64Encoder();
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        for (Tweet tweet : tweets) {
            SNFeed snFeed = new SNFeed();
            snFeed.setId(new Long(tweet.getId()).toString());
            snFeed.setFrom(new Reference(tweet.getFromUser(),tweet.getFromUser()));
            snFeed.setCreatedTime(tweet.getCreatedAt());
            snFeed.setUpdatedTime(tweet.getCreatedAt());

            snFeed.setPhotoFrom(tweet.getProfileImageUrl());
            snFeed.setSocialNetworkType("twitter");
            if(tweet.getRetweetedStatus()!=null){
                snFeed.setFeedType("retweet");
                snFeed.setMessageFromRetweet(tweet.getRetweetedStatus().getText());
                snFeed.setRetweetsCount(tweet.getRetweetedStatus().getRetweetCount());
                snFeed.setFavoritesCount(tweet.getRetweetedStatus().getFavoriteCount());
                snFeed.setPhotoFromRetweet(tweet.getRetweetedStatus().getProfileImageUrl());
                snFeed.setNameFromRetweet(tweet.getRetweetedStatus().getFromUser());
                snFeed.setDateFromRetweet(tweet.getRetweetedStatus().getCreatedAt());
                snFeed.setMessage(tweet.getUser().getName()+" retweeted from:");
            }else{
                snFeed.setFeedType("tweet");
                snFeed.setMessage(tweet.getText());
                snFeed.setRetweetsCount(tweet.getRetweetCount());
                snFeed.setFavoritesCount(tweet.getFavoriteCount());
            }

            snFeeds.add(snFeed);
        }
        return snFeeds;
    }
}
