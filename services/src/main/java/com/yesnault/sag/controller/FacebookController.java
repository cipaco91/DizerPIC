package com.yesnault.sag.controller;

import com.yesnault.sag.SignInUtils;
import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.FacebookFriend;
import com.yesnault.sag.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FacebookController {

    Logger LOGGER = LoggerFactory.getLogger(FacebookController.class);

    @Inject
    private Facebook facebook;


    @Inject
    private FacebookService facebookService;

    @Inject
    private ConnectionRepository connectionRepository;

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

    @RequestMapping(value = "/feedsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<FacebookFriend> feedsFacebook() {
        return facebookService.getFeed();
    }

    @RequestMapping(value = "/friendsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<FacebookFriend> friendsFacebook() {
        return facebookService.getFriendsFacebook();
    }

    @RequestMapping(value = "/profileImageFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String profileImageFacebook() {
        URL url = this.getClass().getClassLoader().getResource("/images");
        InputStream in = new ByteArrayInputStream(facebook.userOperations().getUserProfileImage());
        try {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", new File(
                    url.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "http://graph.facebook.com/" + facebookService.getUserProfile().getId() + "/picture";
    }

    @RequestMapping(value = "/profileFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    FacebookProfile profileFacebook() {
        return facebookService.getUserProfile();
    }


}