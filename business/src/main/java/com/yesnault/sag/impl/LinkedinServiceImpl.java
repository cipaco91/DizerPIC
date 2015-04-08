package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.pojo.LinkedinFeed;
import com.yesnault.sag.pojo.SNFriend;
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
        List<SNFriend> snFriends = new ArrayList<>();
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
        List<SNFriend> snFriends = new ArrayList<>();
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
            List<LinkedinFeed> linkedinFeeds = new ArrayList<>();
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
    public boolean isConnectLinkedin() {
        try{
            return linkedIn.communicationOperations()!=null;
        }catch (Exception e){
            return false;
        }
    }

    private List<SNFriend> getSnFriends(List<LinkedInProfile> linkedInProfiles){
        List<SNFriend> snFriends = new ArrayList<>();
        for(LinkedInProfile linkedInProfile:linkedInProfiles){
            SNFriend snFriend = new SNFriend();
            snFriend.setId(linkedInProfile.getId());
            snFriend.setName(linkedInProfile.getFirstName()+" "+linkedInProfile.getLastName());
            snFriend.setProfileImageUrl(linkedInProfile.getProfilePictureUrl());
            snFriend.setProfileURL(linkedInProfile.getPublicProfileUrl());
//            snFriend.setProfileURL(linkedInProfile.getSiteStandardProfileRequest().getUrl());
            snFriends.add(snFriend);
        }
        return snFriends;
    }
}
