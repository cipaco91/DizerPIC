package com.yesnault.sag.controller;

import com.yesnault.sag.SignInUtils;
import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.interfaces.SocialNetworkService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.ProfileSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.web.ConnectController;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    private FacebookService facebookService;

    @RequestMapping(value = "/postStatus/{facebookFlag}/{twitterFlag}/{linkedinFlag}/{googleFlag}/{message}", method = RequestMethod.GET)
    public SNFeed postStatus(@PathVariable Boolean facebookFlag, @PathVariable Boolean twitterFlag,
                           @PathVariable Boolean linkedinFlag,  @PathVariable Boolean googleFlag,
                           @PathVariable String message,HttpServletRequest httpServletRequest) {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return socialNetworkService.updateStatus(facebookFlag, twitterFlag, linkedinFlag,googleFlag, message,user);
    }

    @RequestMapping(value = "/searchFriends", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<User> searchFriends(@RequestBody String message) {
        socialNetworkService.searchFriends(true,true,true,true,message);
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
    List<SNFriend> friendsProfile(HttpServletRequest httpServletRequest) {
        return socialNetworkService.getFriendsProfile((User)httpServletRequest.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/photosProfile", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    PagedList<Photo> photosProfile() {
        //todo get 3 photos from facebbok,twiiter,linkedn
        return facebookService.getPhotosProfile();
    }

    @RequestMapping(value = "/profileImage", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String profileImageFacebook(HttpServletRequest httpServletRequest) {
        return socialNetworkService.profileImageURL((User)httpServletRequest.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/profileSN", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ProfileSN profileSN(HttpServletRequest httpServletRequest) {
        return socialNetworkService.getProfileUser((User)httpServletRequest.getSession().getAttribute("user"));
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    List<UsersDTO> searchUsers(@RequestBody SearchUsersDTO searchUsersDTO,HttpServletRequest httpServletRequest) {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return socialNetworkService.findUsers(searchUsersDTO,user);
    }

    @RequestMapping(value = "/userDTO", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    SearchUsersDTO getUserDTO() {
        return new SearchUsersDTO();
    }

    @RequestMapping(value = "/feedsSocialNetwork", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFeed> feedsSocialNetwork(HttpServletRequest httpServletRequest) {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return socialNetworkService.getFeed(user);
    }

    @RequestMapping(value = "/favoritesTweets", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFeed> favoritesTweets(HttpServletRequest httpServletRequest) {
        return socialNetworkService.favoritesFeeds();
    }

    @RequestMapping(value = "/refreshFeeds/{socialType}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFeed> refreshFeeds(@PathVariable("socialType") String socialType,HttpServletRequest httpServletRequest) {
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        return socialNetworkService.refreshFeed(socialType,user);
    }
}
