package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.pojo.SNFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@Controller
public class FacebookController {

    Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

    @Inject
    private Facebook facebook;

    @Inject
    private Twitter twitter;


    @Inject
    private FacebookService facebookService;

    @Inject
    private ConnectionRepository connectionRepository;

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

    @RequestMapping(value = "/feedsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> feedsFacebook() {
        return facebookService.getFeed();
    }

    @RequestMapping(value = "/friendsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsFacebook() {
        return facebookService.getFriendsFacebook();
    }

    @RequestMapping(value = "/profileFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    FacebookProfile profileFacebook() {
        return facebookService.getUserProfile();
    }

    @RequestMapping(value = "/isConnectFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectFacebook(){
        return facebookService.isConnectFacebook();
    }


}