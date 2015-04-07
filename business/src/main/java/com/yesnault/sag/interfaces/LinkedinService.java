package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.LinkedinFeed;
import org.springframework.social.linkedin.api.*;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface LinkedinService {

    LinkedInProfileFull getUserProfileFull();

    LinkedInProfile getUserProfile();

    List<LinkedInProfile> getConnections();

    NetworkStatistics getNetworkStatistics();

    void sendMessage(String var1, String var2, String... var3);

    List<LinkedInProfile> getConnections(int start, int count);

    LinkedInProfiles search(SearchParameters var1);

    List<LinkedinFeed> feeds();

    boolean isConnectLinkedin();
}
