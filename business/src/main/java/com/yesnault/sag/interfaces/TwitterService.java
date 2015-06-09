package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.twitter.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface TwitterService {

    List<SNFriend> getFriends(String name);

    List<SNFriend> getFollowers(String name);

    List<DirectMessage> getDirectMessagesReceived();

    List<DirectMessage> getDirectMessagesSent();

    DirectMessage sendDirectMessage(String var1, String var2);

    TwitterProfile getUserProfile();

    void simulateExpiredToken();

    List<Tweet> getTweets(String search);

    List<Tweet> getHomeTimeline();

    List<Tweet> getUserTimeline();

    List<Tweet> getMentions();

    List<Tweet> getFavorites();

    Tweet updateStatus(String var1);

    Trends getLocalTrends(long var1);

    boolean isConnectTwitter(User user);

    List<SNFriend> getSnFriends(CursoredList<TwitterProfile> twitterProfiles,String name);

    List<SNFeed> getFeed();

    List<SNFeed> getMyPosts();

    SNFeed retweet(long tweetId);

    void addToFavorites(long tweetId);
}
