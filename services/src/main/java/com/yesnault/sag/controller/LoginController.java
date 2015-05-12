package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
        User user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "notOK";
        } else {
            if (user.getUserProfile() != null) {
                request.getSession().setAttribute("user", user);
                return "okProfile";
            } else {
                request.getSession().setAttribute("user", user);
                return "okSettings";
            }
        }
    }
}
