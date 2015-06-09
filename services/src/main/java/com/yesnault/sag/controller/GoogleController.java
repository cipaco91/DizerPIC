package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */

@Controller
public class GoogleController {

    Logger LOGGER = LoggerFactory.getLogger(GoogleController.class);

    @Inject
    private GoogleService googleService;

    @RequestMapping(value = "/isConnectGoogle", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectLinkedin(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return googleService.isConnectGoogle(user);
    }

    @RequestMapping(value = "/friendsGoogle/{name}/{age1}/{age2}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsGoogle(@PathVariable String name,@PathVariable Integer age1,@PathVariable Integer age2) {
        String nameValue=null;
        Integer age1Value=null;
        Integer age2Value=null;
        if("null".equals(name)){
            nameValue=null;
        }else{
            nameValue=name;
        }

        if(age1 != 0){
            age1Value=age1;
        }

        if(age2 != 0){
            age2Value=age2;
        }
        return googleService.findFriends(nameValue,age1Value,age2Value);
    }
}
