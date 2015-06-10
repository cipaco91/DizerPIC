package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.*;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.*;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    List<SNFeed> feedsFacebook() {
        return facebookService.getFeed();
    }

    @RequestMapping(value = "/friendsFacebook/{name}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsFacebook(@PathVariable String name) {
        String nameValue=null;
        Integer age1Value=null;
        Integer age2Value=null;
        if("null".equals(name)){
            nameValue=null;
        }else{
            nameValue=name;
        }


        return facebookService.getFriendsFacebook(nameValue);
    }

    @RequestMapping(value = "/profileFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    FacebookProfile profileFacebook() {
        return facebookService.getUserProfile();
    }

    @RequestMapping(value = "/isConnectFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    boolean isConnectFacebook(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return facebookService.isConnectFacebook(user);
    }

    @RequestMapping(value = "/albumsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<AlbumSN> albumsFacebook() {
        return facebookService.getAlbums();
    }

    @RequestMapping(value = "/photosFromAlbum/{albumId}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<PhotoSN> getPhotosFromAlbum(@PathVariable String albumId) {
        return facebookService.getPhotosFromAlbum(albumId);
    }

    @RequestMapping(value = "/addComment/{id}/{message}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String addComment(@PathVariable String id, @PathVariable String message) {
        return facebookService.addComment(id, message);
    }

    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    void addLike(@PathVariable String id) {
        facebookService.addLike(id);
    }

    @RequestMapping(value = "/unlike/{id}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    void unlike(@PathVariable String id) {
        facebookService.unlike(id);
    }

    @RequestMapping(value = "/commentsFeed/{objectId}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<CommentFeed> commentsFeed(@PathVariable String objectId) {
        return facebookService.getComments(objectId);
    }

}