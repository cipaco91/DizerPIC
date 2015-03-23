package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.interfaces.SocialNetworkService;
import com.yesnault.sag.interfaces.TwitterService;
import com.yesnault.sag.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Controller
public class SocialNetworkController {

    @Inject
    private SocialNetworkService socialNetworkService;

    @RequestMapping(value = "/postStatus/{facebookFlag}/{twitterFlag}/{linkedinFlag}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postStatus(@PathVariable Boolean facebookFlag, @PathVariable Boolean twitterFlag,
                           @PathVariable Boolean linkedinFlag, @RequestBody String message) {
        socialNetworkService.updateStatus(facebookFlag, twitterFlag, linkedinFlag, message);
    }

    @RequestMapping(value = "/searchFriends", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<User> searchFriends(@RequestBody String message) {
        socialNetworkService.searchFriends(true,true,true,message);
        return new ArrayList<User>();
    }


}
