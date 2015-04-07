package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.LinkedinFeed;
import com.yesnault.sag.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.linkedin.api.LinkedInNetworkUpdate;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LinkedinController {

    Logger LOGGER = LoggerFactory.getLogger(LinkedinController.class);

    @Inject
    private LinkedinService linkedinService;

    @RequestMapping(value = "/feedsLinkedin", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<LinkedinFeed> feedsLinkedin() {
        return linkedinService.feeds();
    }

    @RequestMapping(value = "/friendsLinkedin", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<LinkedInProfile> friendsLinkedin() {
        return linkedinService.getConnections();
    }

    @RequestMapping(value = "/isConnectLinkedin", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectLinkedin(){
        return linkedinService.isConnectLinkedin();
    }






}