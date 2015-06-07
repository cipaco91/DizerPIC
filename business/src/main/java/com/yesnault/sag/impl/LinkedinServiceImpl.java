package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.LinkedinFeed;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.linkedin.api.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "linkedinServiceImpl")
@Transactional
public class LinkedinServiceImpl implements LinkedinService{

    @Inject
    private LinkedIn linkedIn;

    @Inject
    private UserService userService;

    @Override
    public LinkedInProfileFull getUserProfileFull() {
        if(linkedIn!=null) {
            return linkedIn.profileOperations().getUserProfileFull();
        }
        return null;
    }

    @Override
    public LinkedInProfile getUserProfile() {
        if(linkedIn!=null) {
            return linkedIn.profileOperations().getUserProfile();
        }
        return null;
    }

    @Override
    public List<SNFriend> getConnections() {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if(linkedIn!=null) {
            List<LinkedInProfile> linkedInProfiles= linkedIn.connectionOperations().getConnections();
            snFriends=getSnFriends(linkedInProfiles);
        }
        return snFriends;
    }

    @Override
    public NetworkStatistics getNetworkStatistics() {
        if(linkedIn!=null) {
            return linkedIn.connectionOperations().getNetworkStatistics();
        }
        return null;
    }

    @Override
    public void sendMessage(String var1, String var2, String... var3) {

    }

    @Override
    public List<SNFriend> getConnections(int start, int count) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if(linkedIn!=null) {
            List<LinkedInProfile> linkedInProfiles= linkedIn.connectionOperations().getConnections(start, count);
            snFriends=getSnFriends(linkedInProfiles);
        }
        return snFriends;
    }

    @Override
    public LinkedInProfiles search(SearchParameters var1) {
        if(linkedIn!=null) {
            return linkedIn.profileOperations().search(var1);
        }
        return null;
    }

    @Override
    public List<LinkedinFeed> feeds() {
        if(linkedIn!=null) {
            List<LinkedInNetworkUpdate> linkedInNetworkUpdateList = linkedIn.networkUpdateOperations().getNetworkUpdates();
            List<LinkedinFeed> linkedinFeeds = new ArrayList<LinkedinFeed>();
            for (LinkedInNetworkUpdate linkedInNetworkUpdate : linkedInNetworkUpdateList) {
                LinkedinFeed linkedinFeed = new LinkedinFeed();
                linkedinFeed.setProfileImageUrl(linkedInNetworkUpdate.getUpdateContent().getProfilePictureUrl());
                if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentShare) {
                    linkedinFeed.setText(((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare() != null ?
                            ((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare().getContent().getDescription() :
                            "nullnullnullnullnull");
                }
                if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentViral) {
                    linkedinFeed.setText("Like post " + ((UpdateContentShare) ((UpdateContentViral) linkedInNetworkUpdate.getUpdateContent()).getUpdateAction().
                            getUpdateContent()).getCurrentShare().getContent().getDescription());
                }
                if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentConnection) {
                    linkedinFeed.setText("You connected with " + ((UpdateContentConnection) linkedInNetworkUpdate.getUpdateContent()).getFirstName() + " " +
                            ((UpdateContentConnection) linkedInNetworkUpdate.getUpdateContent()).getLastName());
                }
                linkedinFeeds.add(linkedinFeed);
            }
            return linkedinFeeds;
        }
        return null;
    }

    @Override
    public boolean isConnectLinkedin(User user) {
        try{
            List<User> listUsers = userService.findByUsername(user.getUsername());
            if(listUsers!=null&&listUsers.size()>0) {
                UserProfile userProfile=listUsers.get(0).getUserProfile();
                if (new Boolean(true).equals(userProfile.getLinkedinFlag())) {
                    return linkedIn.communicationOperations() != null;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean isConnectLinkedinMenu() {
        try{
            return linkedIn.communicationOperations() != null;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<SNFriend> getSnFriends(List<LinkedInProfile> linkedInProfiles){
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        for(LinkedInProfile linkedInProfile:linkedInProfiles){
            SNFriend snFriend = new SNFriend();
            snFriend.setId(linkedInProfile.getId());
            snFriend.setName(linkedInProfile.getFirstName()+" "+linkedInProfile.getLastName());
            if(linkedInProfile.getProfilePictureUrl()!=null) {
                snFriend.setProfileImageUrl(linkedInProfile.getProfilePictureUrl());
            }else continue;
            snFriend.setProfileURL(linkedInProfile.getPublicProfileUrl());
//            snFriend.setProfileURL(linkedInProfile.getSiteStandardProfileRequest().getUrl());
            snFriend.setSocialNetworkType("linkedin");
            snFriend.setSocialNetworkTypePicture("images/linkedin_logo.png");
            snFriends.add(snFriend);
        }
        return snFriends;
    }

    @Override
    public List<SNFeed> getFeed() {
        if(linkedIn!=null) {
            List<LinkedInNetworkUpdate> linkedInNetworkUpdateList = linkedIn.networkUpdateOperations().getNetworkUpdates();
            return getSnFeeds(linkedInNetworkUpdateList);
        }
        return null;
    }

    @Override
    public List<SNFeed> getMyPosts() {
        return null;
    }

    private List<SNFeed> getSnFeeds(List<LinkedInNetworkUpdate> linkedInNetworkUpdates) {
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        for (LinkedInNetworkUpdate linkedInNetworkUpdate : linkedInNetworkUpdates) {
            SNFeed snFeed = new SNFeed();
            snFeed.setPhotoFrom(linkedInNetworkUpdate.getUpdateContent().getProfilePictureUrl());
            snFeed.setName(linkedInNetworkUpdate.getUpdateContent().getFirstName()+" "+linkedInNetworkUpdate.getUpdateContent().getLastName());

            if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentCompany) {
//                snFeed.setMessage("Like post " + ((UpdateContentShare) ((UpdateContentViral) linkedInNetworkUpdate.getUpdateContent()).getUpdateAction().
//                        getUpdateContent()).getCurrentShare().getComment());
            }

//           if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentShare) {
//                snFeed.setMessage(((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare() != null ?
//                        ((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare().getContent()!=null?
//                ((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare().getContent().getDescription() :"Like post":
//                        "nullnullnullnullnull");
//            }

//            if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentViral) {
//                snFeed.setMessage("Like post " + ((UpdateContentShare) ((UpdateContentViral) linkedInNetworkUpdate.getUpdateContent()).getUpdateAction().
//                        getUpdateContent()).getCurrentShare().getComment());
//            }
//            if (linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentConnection) {
//                snFeed.setMessage("You connected with " + ((UpdateContentConnection) linkedInNetworkUpdate.getUpdateContent()).getFirstName() + " " +
//                        ((UpdateContentConnection) linkedInNetworkUpdate.getUpdateContent()).getLastName());
//            }
            snFeed.setLikesCount(Integer.valueOf(linkedInNetworkUpdate.getNumLikes()));
            snFeed.setFrom(new
                    Reference(linkedInNetworkUpdate.getUpdateContent().getFirstName()+" "+linkedInNetworkUpdate.getUpdateContent().getFirstName(),
                              linkedInNetworkUpdate.getUpdateContent().getFirstName()+" "+linkedInNetworkUpdate.getUpdateContent().getFirstName()));
            snFeed.setCreatedTime(linkedInNetworkUpdate.getTimestamp());
            snFeed.setSocialNetworkType("linkedin");
            snFeeds.add(snFeed);
        }
        return snFeeds;
    }
}
