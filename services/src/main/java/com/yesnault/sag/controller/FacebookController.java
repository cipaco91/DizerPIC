package com.yesnault.sag.controller;

import com.yesnault.sag.SignInUtils;
import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FacebookController {

    Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

    @Inject
    private ConnectController connectController;

    @Inject
    private FacebookService facebookService;

    @Inject
    private ConnectionRepository connectionRepository;

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

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

    @RequestMapping(value = "/login",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String login(NativeWebRequest nativeWebRequest) {
        RedirectView redirectView = connectController.connect("facebook", nativeWebRequest);
        SignInUtils.signin("ciprian");
        return redirectView.getUrl();
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET, params="code")
    @ResponseBody
    public RedirectView login2(NativeWebRequest nativeWebRequest) {
        RedirectView redirectView = connectController.oauth2Callback("facebook", nativeWebRequest);
        SignInUtils.signin("ciprian");
        redirectView.setUrl("/#/home");
        return redirectView;
    }


}