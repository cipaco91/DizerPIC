package com.yesnault.sag.controller;

import com.yesnault.sag.SignInUtils;
import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.interfaces.SocialNetworkService;
import com.yesnault.sag.interfaces.TwitterService;
import com.yesnault.sag.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Controller
public class SocialNetworkController {


    @Inject
    private ConnectController connectController;

    @Inject
    private SocialNetworkService socialNetworkService;

    @Inject
    private LinkedinService linkedinService;

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

    @RequestMapping(value = "/login/{providerId}",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String login(@PathVariable("providerId") String providerId,NativeWebRequest nativeWebRequest) {
        RedirectView redirectView = connectController.connect(providerId, nativeWebRequest);
        SignInUtils.signin("ciprian");
        return redirectView.getUrl();
    }

    @RequestMapping(value = "/login/{providerId}", method=RequestMethod.GET, params="code")
    @ResponseBody
    public RedirectView loginWithCode(@PathVariable("providerId") String providerId,NativeWebRequest nativeWebRequest) {
        RedirectView redirectView = connectController.oauth2Callback(providerId, nativeWebRequest);
        SignInUtils.signin("ciprian");
        redirectView.setUrl("/#/home");
        return redirectView;
    }

    @RequestMapping(value = "/login/{providerId}", method=RequestMethod.GET, params="oauth_token")
    @ResponseBody
    public RedirectView loginWithOauthToken(@PathVariable("providerId") String providerId,NativeWebRequest nativeWebRequest) {
        RedirectView redirectView = connectController.oauth1Callback(providerId, nativeWebRequest);
        SignInUtils.signin("ciprian");
        redirectView.setUrl("/#/home");
        return redirectView;
    }

    @RequestMapping(value = "/friendsProfile", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<LinkedInProfile> friendsProfile() {
        //todo get 3 friends from facebbok,twiiter,linkedn
        return linkedinService.getConnections(1,10);
    }


}
