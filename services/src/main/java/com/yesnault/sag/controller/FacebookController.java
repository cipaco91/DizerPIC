package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FacebookController {

    Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

    @Inject
    private FacebookService facebookService;

    @RequestMapping(value = "/feedsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    PagedList<Post> feedsFacebook() {
        return facebookService.getFeed();
    }

    @RequestMapping(value = "/friendsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    PagedList<Reference> friendsFacebook() {
        return facebookService.getFriendsFacebook();
    }


}