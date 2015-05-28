package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.*;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.ProfileSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.repository.UserProfileRepository;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;
import org.apache.geronimo.mail.util.Base64;
import org.springframework.social.facebook.api.*;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.LinkedInProfiles;
import org.springframework.social.linkedin.api.SearchParameters;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
@Service(value = "socialNetworkServiceImpl")
@Transactional
public class SocialNetworkServiceImpl implements SocialNetworkService {

    @Inject
    private Facebook facebook;

    @Inject
    private LinkedIn linkedIn;

    @Inject
    private Twitter twitter;

    @Inject
    private Google google;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private UserService userService;

    @Inject
    private FacebookService facebookService;

    @Inject
    private LinkedinService linkedinService;

    @Inject
    private TwitterService twitterService;

    @Inject
    private GoogleService googleService;

    @Override
    public void updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, Boolean googleFlag, String message) {
        if (facebookFlag) {
            facebook.feedOperations().updateStatus(message);
        }
        if (twitterFlag) {
            twitter.timelineOperations().updateStatus(message);
        }
        if (linkedinFlag) {
            linkedIn.networkUpdateOperations().createNetworkUpdate(message);
        }

        if (googleFlag) {
            google.plusOperations().getActivities("").hashCode();
        }
    }

    @Override
    public void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, Boolean googleFlag, String search) {
        if (facebookFlag) {
            facebook.userOperations().search(search);
        }
        if (twitterFlag) {
            twitter.searchOperations().search(search);
        }
        if (linkedinFlag) {
            SearchParameters searchParameters = new SearchParameters();
            searchParameters.setFirstName(search);
            linkedIn.profileOperations().search(searchParameters);
        }

        if(googleFlag){
            google.plusOperations().searchPeople(search,null);
        }
    }


    @Override
    public String profileImageURL(User user) {
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if ("facebook".equals(userProfile.getFromProfileImage())) {
            BASE64Encoder encoder = new BASE64Encoder();
            return "data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage());
        } else if ("twitter".equals(userProfile.getFromProfileImage())) {
            return twitter.userOperations().getUserProfile().getProfileImageUrl();
        } else if ("google".equals(userProfile.getFromProfileImage())) {
            return google.plusOperations().getGoogleProfile().getImageUrl();
        } else {
            return linkedinService.getUserProfile().getProfilePictureUrl();
        }
    }

    @Override
    public ProfileSN getProfileUser(User user) {
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if ("facebook".equals(userProfile.getFromProfileAbout())) {
            return new ProfileSN(facebook.userOperations().getUserProfile().getId(),
                    facebook.userOperations().getUserProfile().getGender(), facebook.userOperations().getUserProfile().getName(),
                    facebook.userOperations().getUserProfile().getEmail(), facebook.userOperations().getUserProfile().getBirthday(),
                    facebook.userOperations().getUserProfile().getAbout(), facebook.userOperations().getUserProfile().getLocation().getName(),
                    facebook.userOperations().getUserProfile().getRelationshipStatus());
        } else if ("twitter".equals(userProfile.getFromProfileAbout())) {
            return new ProfileSN(Long.toString(twitter.userOperations().getUserProfile().getId()),
                    facebook.userOperations().getUserProfile().getGender(), twitter.userOperations().getUserProfile().getName(),
                    facebook.userOperations().getUserProfile().getEmail(), facebook.userOperations().getUserProfile().getBirthday(),
                    facebook.userOperations().getUserProfile().getAbout());
        } else if ("google".equals(userProfile.getFromProfileAbout())) {
            Person person=google.plusOperations().getGoogleProfile();
            return new ProfileSN(person.getId(),
                    person.getGender(), facebook.userOperations().getUserProfile().getName(),
                    linkedIn.profileOperations().getUserProfileFull().getEmailAddress(), person.getBirthday().toString(),
                    person.getAboutMe(), "Bucuresti,Romania",
                    "in_a_relationship".equals(person.getRelationshipStatus())?"In a relantionship":"Single");
        } else {
            return new ProfileSN(linkedIn.profileOperations().getProfileId(), facebook.userOperations().getUserProfile().getGender(), linkedIn.profileOperations().getUserProfileFull().getFirstName() +
                    " " + linkedIn.profileOperations().getUserProfileFull().getLastName(),
                    linkedIn.profileOperations().getUserProfileFull().getEmailAddress(),
                    facebook.userOperations().getUserProfile().getBirthday(),
                    linkedIn.profileOperations().getUserProfileFull().getSummary(),
                    linkedIn.profileOperations().getUserProfileFull().getLocation().getName(),
                    facebook.userOperations().getUserProfile().getRelationshipStatus());
        }
    }

    @Override
    public List<SNFriend> getFriendsProfile(User user) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if (user != null) {
            UserProfile userProfile = userProfileRepository.findByUser(user);
            if ("facebook".equals(userProfile.getFromProfileFriends())) {
            } else if ("twitter".equals(userProfile.getFromProfileFriends())) {
                List<SNFriend> snFriendList = twitterService.getFollowers();
                if (snFriendList.size() > 10) {
                    snFriends.addAll(twitterService.getFriends().subList(0, 10));
                } else {
                    snFriends.addAll(twitterService.getFriends());
                }
            } else if ("google".equals(userProfile.getFromProfileFriends())) {
                List<SNFriend> snFriendList = googleService.findFriends();
                if (snFriendList.size() > 10) {
                    snFriends.addAll(snFriendList.subList(0, 10));
                } else {
                    snFriends.addAll(snFriendList);
                }
            } else {
                snFriends.addAll(linkedinService.getSnFriends(linkedIn.connectionOperations().getConnections(1, 11)));
            }
            return snFriends;
        }
        return snFriends;
    }

    @Override
    public List<UsersDTO> findUsers(SearchUsersDTO searchUsersDTO) {
        List<UsersDTO> usersDTOs = new ArrayList<UsersDTO>();
        BASE64Encoder encoder = new BASE64Encoder();
        PagingParameters pagingParameters = new PagingParameters(10, 10, new Long(1000), new Long(1000));

        PagedList<Reference> references = facebook.userOperations().search(searchUsersDTO.getName());
        if (references != null && references.size() > 0) {
//            List<Reference> referencesList=references.subList(0,19);
            for (Reference reference : references) {
                if (reference.getName().equals(searchUsersDTO.getName())) {
                    FacebookProfile facebookProfile = facebook.userOperations().getUserProfile(reference.getId());
                    UsersDTO usersDTO = new UsersDTO();
                    usersDTO.setName(facebookProfile.getName());
                    usersDTO.setFirstName(facebookProfile.getFirstName());
                    usersDTO.setLastName(facebookProfile.getLastName());
                    usersDTO.setId(facebookProfile.getId());
                    usersDTO.setProfileURL(facebookProfile.getLink());
                    usersDTO.setImageURL("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(reference.getId())));
                    usersDTO.setSocialNetType("facebook");
                    usersDTOs.add(usersDTO);
                }
            }
        } else {
            for (Reference reference : references) {
                FacebookProfile facebookProfile = facebook.userOperations().getUserProfile(reference.getId());
                UsersDTO usersDTO = new UsersDTO();
                usersDTO.setName(facebookProfile.getName());
                usersDTO.setFirstName(facebookProfile.getFirstName());
                usersDTO.setLastName(facebookProfile.getLastName());
                usersDTO.setId(facebookProfile.getId());
                usersDTO.setProfileURL(facebookProfile.getLink());
                usersDTO.setImageURL("data:image/jpeg;base64," + encoder.encode(facebook.userOperations().getUserProfileImage(reference.getId())));
                usersDTO.setSocialNetType("facebook");
                usersDTOs.add(usersDTO);
            }
        }
        List<TwitterProfile> twitterProfiles = twitter.userOperations().searchForUsers(searchUsersDTO.getName());
        if (twitterProfiles != null && twitterProfiles.size() > 0) {
            for (TwitterProfile twitterProfile : twitterProfiles) {
                UsersDTO userDTO = new UsersDTO();
                userDTO.setName(twitterProfile.getName());
                userDTO.setId(String.valueOf(twitterProfile.getId()));
                userDTO.setSocialNetType("twitter");
                userDTO.setImageURL(twitterProfile.getProfileImageUrl());
                userDTO.setProfileURL(twitterProfile.getProfileUrl());
                usersDTOs.add(userDTO);
            }
        }

        List<Person> personProfiles = google.plusOperations().personQuery().searchFor(searchUsersDTO.getName()).getPage().getItems();
        if (personProfiles != null && personProfiles.size() > 0) {
            for (Person person : personProfiles) {
                UsersDTO userDTO = new UsersDTO();
                userDTO.setName(person.getDisplayName());
                userDTO.setId(person.getId());
                userDTO.setSocialNetType("google");
                userDTO.setImageURL(person.getImageUrl());
                userDTO.setProfileURL(person.getUrl());
                usersDTOs.add(userDTO);
            }
        }

        //todo wait approved from linkedn support
//        SearchParameters searchParameters=new SearchParameters();
////        searchParameters.setFirstName("Ciprian");
////        searchParameters.setLastName("Paraschivescu");
//        searchParameters.setKeywords("Hacker");
//        LinkedInProfiles linkedInProfiles=linkedIn.profileOperations().search(searchParameters);

//        UsersDTO usersDTO = new UsersDTO();
//        usersDTO.setName("Ciprian Paraschivescu");
//        usersDTO.setFirstName("Ciprian");
//        usersDTO.setLastName("Paraschivescu");
//        usersDTO.setId("123");
//        usersDTO.setSocialNetType("facebook");
//        usersDTOs.add(usersDTO);
        return usersDTOs;
    }

    @Override
    public List<SNFeed> getFeed() {
        List<SNFeed> snFeeds = facebookService.getFeed();
        snFeeds.addAll(twitterService.getFeed());
//        try {
//            snFeeds.addAll(linkedinService.getFeed());
//        }catch(Exception e){
//        }
        Collections.shuffle(snFeeds);
        return snFeeds;
    }
}
