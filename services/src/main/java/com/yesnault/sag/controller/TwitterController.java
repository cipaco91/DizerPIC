package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.TwitterService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TwitterController {

    Logger LOGGER = LoggerFactory.getLogger(TwitterController.class);

    @Inject
    private TwitterService twitterService;

    @RequestMapping(value = "/feedsTwitter", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Tweet> feedsTwitter() {
        return twitterService.getHomeTimeline();
    }

    @RequestMapping(value = "/friendsTwitter", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsTwitter() {
        return twitterService.getFriends();
    }

    @RequestMapping(value = "/isConnectTwitter", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectTwitter(){
        return twitterService.isConnectTwitter();
    }
}