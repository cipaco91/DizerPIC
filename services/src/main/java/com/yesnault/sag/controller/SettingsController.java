package com.yesnault.sag.controller;

import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.util.WizzardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CParaschivescu on 4/27/2015.
 */
@Controller
public class SettingsController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/wizzardDTO", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    WizzardDTO getWizzardDTO(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return new WizzardDTO();
        } else {
            List<User> listUsers = userService.findByUsername(user.getUsername());
            WizzardDTO wizzardDTO = new WizzardDTO();
            if (listUsers != null && listUsers.size() > 0) {
                UserProfile userProfile = listUsers.get(0).getUserProfile();
                wizzardDTO.setIsFacebook(userProfile.getFacebookFlag());
                wizzardDTO.setIsGoogle(userProfile.getGoogleFlag());
                wizzardDTO.setIsLinkedin(userProfile.getLinkedinFlag());
                wizzardDTO.setIsTwitter(userProfile.getTwitterFlag());
                wizzardDTO.setFirstName(user.getFirstname());
                wizzardDTO.setLastName(user.getLastname());
                wizzardDTO.setEmail("setEmail");
                wizzardDTO.setProfileAbout(userProfile.getFromProfileAbout());
                wizzardDTO.setProfileCover(userProfile.getFromProfileCover());
                wizzardDTO.setProfileFriend(userProfile.getFromProfileFriends());
                wizzardDTO.setProfileImage(userProfile.getFromProfileImage());
                wizzardDTO.setProfileName(userProfile.getFromProfileName());
            }
            return wizzardDTO;
        }
    }

    @RequestMapping(value = "/finishWizzardProfile", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Boolean finishWizzardProfile(@RequestBody WizzardDTO wizzardDTO, HttpServletRequest httpServletRequest) {
        return userService.saveUserWizzardProfile(wizzardDTO, (User) httpServletRequest.getSession().getAttribute("user"));
    }

}
