package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.SocialNetworkService;
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
public class SocialNetworkServiceImpl implements SocialNetworkService{

    @Inject
    private Facebook facebook;

    @Inject
    private LinkedIn linkedIn;

    @Inject
    private Twitter twitter;

    @Override
    public void updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String message) {
        if(facebookFlag){
            facebook.feedOperations().updateStatus(message);
        }
        if(twitterFlag){
            twitter.timelineOperations().updateStatus(message);
        }
        if(linkedinFlag){
            linkedIn.networkUpdateOperations().createNetworkUpdate(message);
        }
    }

    @Override
    public void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String search) {
        if(facebookFlag){
          facebook.userOperations().search(search);
        }
        if(twitterFlag){
           twitter.searchOperations().search(search);
        }
        if(linkedinFlag){
            SearchParameters searchParameters=new SearchParameters();
            searchParameters.setFirstName(search);
            linkedIn.profileOperations().search(searchParameters);
        }
    }
}
