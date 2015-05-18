package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFriend;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by CParaschivescu on 4/14/2015.
 */
@Controller
@Scope("session")
public class LoginController {

    Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Inject
    private UserService userService;

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String login(@PathVariable String username, @PathVariable String password, HttpServletRequest request) {
        return userService.login(username,password,request);
    }

    @RequestMapping(value = "/signUp/{username}/{password}/{firstName}/{lastName}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String signUp(@PathVariable String username, @PathVariable String password,@PathVariable String firstName,
                  @PathVariable String lastName, HttpServletRequest request) {
        return userService.signUp(username, password,firstName,lastName);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String logout(HttpServletRequest request) {
        userService.logoutFromSocialNetworks((User)request.getSession().getAttribute("user"));
        request.getSession().invalidate();
        return "okLogout";
    }
}
