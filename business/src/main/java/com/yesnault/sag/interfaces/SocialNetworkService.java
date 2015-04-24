package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.ProfileSN;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface SocialNetworkService {

    void updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String message);

    void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String search);

    String profileImageURL();

    ProfileSN getProfileUser();
}
