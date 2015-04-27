package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.repository.UserProfileRepository;
import com.yesnault.sag.repository.UserRepository;
import com.yesnault.sag.util.WizzardDTO;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by CParaschivescu on 1/20/2015.
 */
@Service(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private Facebook facebook;

    @Inject
    private Twitter twitter;

    @Inject
    private LinkedIn linkedIn;

//    @Inject
//    private Google google;


    @Override
    public List<User> findByLastname(String lastname) {
        try {
            LinkedInProfile linkedInProfile=linkedIn.profileOperations().getUserProfile();
            TwitterProfile twitterProfile = twitter.userOperations().getUserProfile();
            PagedList<Post> posts = facebook.feedOperations().getFeed();
//            Person person= google.plusOperations().getGoogleProfile();
        }catch(Exception e){
        }
        return userRepository.findByLastname(lastname);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username,password);
        UserProfile userProfile=userProfileRepository.findByUser(user);
        return user;
    }

    @Override
    public Boolean saveUserWizzardProfile(WizzardDTO wizzardDTO) {
        UserProfile userProfile=new UserProfile();
        return null;
    }
}
