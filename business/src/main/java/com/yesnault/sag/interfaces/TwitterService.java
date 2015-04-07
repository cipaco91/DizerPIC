package com.yesnault.sag.interfaces;

import org.springframework.social.twitter.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface TwitterService {

    CursoredList<TwitterProfile> getFriends();

    CursoredList<TwitterProfile> getFollowers();

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

    boolean isConnectTwitter();
}
