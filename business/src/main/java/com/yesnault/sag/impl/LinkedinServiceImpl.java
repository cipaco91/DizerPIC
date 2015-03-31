package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.LinkedinService;
import com.yesnault.sag.pojo.LinkedinFeed;
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
        return linkedIn.profileOperations().getUserProfileFull();
    }

    @Override
    public LinkedInProfile getUserProfile() {
        return linkedIn.profileOperations().getUserProfile();
    }

    @Override
    public List<LinkedInProfile> getConnections() {
        return linkedIn.connectionOperations().getConnections();
    }

    @Override
    public NetworkStatistics getNetworkStatistics() {
        return linkedIn.connectionOperations().getNetworkStatistics();
    }

    @Override
    public void sendMessage(String var1, String var2, String... var3) {

    }

    @Override
    public List<LinkedInProfile> getConnections(int start, int count) {
        return linkedIn.connectionOperations().getConnections(start,count);
    }

    @Override
    public LinkedInProfiles search(SearchParameters var1) {
        return linkedIn.profileOperations().search(var1);
    }

    @Override
    public List<LinkedinFeed> feeds() {
        List<LinkedInNetworkUpdate> linkedInNetworkUpdateList= linkedIn.networkUpdateOperations().getNetworkUpdates();
        List<LinkedinFeed> linkedinFeeds= new ArrayList<>();
        for(LinkedInNetworkUpdate linkedInNetworkUpdate:linkedInNetworkUpdateList){
            LinkedinFeed linkedinFeed=new LinkedinFeed();
            linkedinFeed.setProfileImageUrl(linkedInNetworkUpdate.getUpdateContent().getProfilePictureUrl());
            if(linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentShare){
                linkedinFeed.setText(((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare() != null ?
                        ((UpdateContentShare) linkedInNetworkUpdate.getUpdateContent()).getCurrentShare().getContent().getDescription():
                          "nullnullnullnullnull");
            }
            if(linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentViral){
                linkedinFeed.setText("Like post "+((UpdateContentShare)((UpdateContentViral)linkedInNetworkUpdate.getUpdateContent()).getUpdateAction().
                        getUpdateContent()).getCurrentShare().getContent().getDescription());
            }
            if(linkedInNetworkUpdate.getUpdateContent() instanceof UpdateContentConnection){
                linkedinFeed.setText("You connected with "+((UpdateContentConnection)linkedInNetworkUpdate.getUpdateContent()).getFirstName()+" "+
                        ((UpdateContentConnection)linkedInNetworkUpdate.getUpdateContent()).getLastName());
            }
            linkedinFeeds.add(linkedinFeed);
        }
        return linkedinFeeds;
    }
}
