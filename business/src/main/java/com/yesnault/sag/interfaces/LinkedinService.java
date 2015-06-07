package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.LinkedinFeed;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.linkedin.api.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface LinkedinService {

    LinkedInProfileFull getUserProfileFull();

    LinkedInProfile getUserProfile();

    List<SNFriend> getConnections();

    NetworkStatistics getNetworkStatistics();

    void sendMessage(String var1, String var2, String... var3);

    List<SNFriend> getConnections(int start, int count);

    LinkedInProfiles search(SearchParameters var1);

    List<LinkedinFeed> feeds();

    boolean isConnectLinkedin(User user);

    boolean isConnectLinkedinMenu();

    List<SNFriend> getSnFriends(List<LinkedInProfile> linkedInProfiles);

    List<SNFeed> getFeed();

    List<SNFeed> getMyPosts();

}
