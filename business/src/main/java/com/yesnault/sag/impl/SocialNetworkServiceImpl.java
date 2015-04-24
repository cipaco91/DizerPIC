package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.*;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.ProfileSN;
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

    @Override
    public ProfileSN getProfileUser() {
        User user = userService.findByUsernameAndPassword("admin", "1");
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if ("facebook".equals(userProfile.getFromProfileAbout())) {
            return new ProfileSN(facebook.userOperations().getUserProfile().getId(),
                    facebook.userOperations().getUserProfile().getGender(),facebook.userOperations().getUserProfile().getName(),
                    facebook.userOperations().getUserProfile().getEmail(),facebook.userOperations().getUserProfile().getBirthday(),
                    facebook.userOperations().getUserProfile().getAbout(), facebook.userOperations().getUserProfile().getLocation().getName(),
                    facebook.userOperations().getUserProfile().getRelationshipStatus());
        } else if ("twitter".equals(userProfile.getFromProfileAbout())) {
            return new ProfileSN(Long.toString(twitter.userOperations().getUserProfile().getId()),
                    facebook.userOperations().getUserProfile().getGender(),twitter.userOperations().getUserProfile().getName(),
                    facebook.userOperations().getUserProfile().getEmail(),facebook.userOperations().getUserProfile().getBirthday(),
                    facebook.userOperations().getUserProfile().getAbout());
        } else {
            return new ProfileSN(linkedIn.profileOperations().getProfileId(),linkedIn.profileOperations().getUserProfileFull().getFirstName()+
                    " "+linkedIn.profileOperations().getUserProfileFull().getLastName(),
                    facebook.userOperations().getUserProfile().getGender(),
                    linkedIn.profileOperations().getUserProfileFull().getEmailAddress(),
                    linkedIn.profileOperations().getUserProfileFull().getDateOfBirth().getDay()+"/"+
                            linkedIn.profileOperations().getUserProfileFull().getDateOfBirth().getMonth()+"/"+
                            linkedIn.profileOperations().getUserProfileFull().getDateOfBirth().getYear(),
                    linkedIn.profileOperations().getUserProfileFull().getSummary(),linkedIn.profileOperations().getUserProfileFull().getLocation().getName(),
                    facebook.userOperations().getUserProfile().getRelationshipStatus());
        }
    }
}
