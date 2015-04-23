package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.*;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.repository.UserProfileRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "socialNetworkServiceImpl")
@Transactional
public class SocialNetworkServiceImpl implements SocialNetworkService {

    @Inject
    private Facebook facebook;

    @Inject
    private LinkedIn linkedIn;

    @Inject
    private Twitter twitter;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private UserService userService;

    @Inject
    private FacebookService facebookService;

    @Inject
    private LinkedinService linkedinService;

    @Inject
    private TwitterService twitterService;

    @Override
    public void updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String message) {
        if (facebookFlag) {
            facebook.feedOperations().updateStatus(message);
        }
        if (twitterFlag) {
            twitter.timelineOperations().updateStatus(message);
        }
        if (linkedinFlag) {
            linkedIn.networkUpdateOperations().createNetworkUpdate(message);
        }
    }

    @Override
    public void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String search) {
        if (facebookFlag) {
            facebook.userOperations().search(search);
        }
        if (twitterFlag) {
            twitter.searchOperations().search(search);
        }
        if (linkedinFlag) {
            SearchParameters searchParameters = new SearchParameters();
            searchParameters.setFirstName(search);
            linkedIn.profileOperations().search(searchParameters);
        }
    }


    @Override
    public String profileImageURL() {
        User user = userService.findByUsernameAndPassword("admin", "1");
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if ("facebook".equals(userProfile.getFromProfileImage())) {
            return "http://graph.facebook.com/" + facebook.userOperations().getUserProfile().getId() + "/picture";
        } else if ("twitter".equals(userProfile.getFromProfileImage())) {
            return twitter.userOperations().getUserProfile().getProfileImageUrl();
        } else {
            return linkedinService.getUserProfile().getProfilePictureUrl();
        }
    }
}
