package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.pojo.AlbumSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
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

    @RequestMapping(value = "/friendsFacebook", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<SNFriend> friendsFacebook() {
        return facebookService.getFriendsFacebook();
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
    boolean isConnectFacebook(){
        return facebookService.isConnectFacebook();
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
    List<Photo> getPhotosFromAlbum(@PathVariable String albumId) {
        return facebookService.getPhotosFromAlbum(albumId);
    }

    @RequestMapping(value = "/addComment/{id}/{message}", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    String addComment(@PathVariable String id,@PathVariable String message) {
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






}