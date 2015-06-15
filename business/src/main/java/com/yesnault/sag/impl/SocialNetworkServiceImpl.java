package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.*;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.CommentFeed;
import com.yesnault.sag.pojo.ProfileSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.repository.UserProfileRepository;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;
import org.springframework.social.facebook.api.*;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public SNFeed updateStatus(Boolean facebookFlag, Boolean twitterFlag,
                               Boolean linkedinFlag, Boolean googleFlag, String message, User user) {
//        if (facebookFlag) {
//            facebook.feedOperations().post(facebook.userOperations().getUserProfile().getId(),message);
//        }
//        if (twitterFlag) {
//            twitter.timelineOperations().updateStatus(message);
//        }

        if (linkedinFlag) {
            linkedIn.networkUpdateOperations().createNetworkUpdate(message);
        }

        if (googleFlag) {
        }

        SNFeed snFeed = new SNFeed();
        snFeed.setMessage(message);
        snFeed.setPhotoFrom(profileImageURL(user));
        snFeed.setSocialNetworkType("dizerpic");
        snFeed.setFrom(new Reference(google.plusOperations().getGoogleProfile().getId(),
                google.plusOperations().getGoogleProfile().getDisplayName()));
        snFeed.setCommentsFeeds(new ArrayList<CommentFeed>());
        snFeed.setLikesCount(0);
        snFeed.setCreatedTime(new Date());
        snFeed.setFacebookFlag(facebookFlag);
        snFeed.setTwitterFlag(twitterFlag);
        snFeed.setGoogleFlag(googleFlag);

        return snFeed;
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

        if (googleFlag) {
            google.plusOperations().searchPeople(search, null);
        }
    }


    @Override
    public String profileImageURL(User user) {
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if (userProfile != null) {
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
        return "noPhoto";
    }

    @Override
    public ProfileSN getProfileUser(User user) {
        UserProfile userProfile = userProfileRepository.findByUser(user);
        if (userProfile != null) {
            if ("facebook".equals(userProfile.getFromProfileAbout())) {
                ProfileSN profileSN= new  ProfileSN(facebook.userOperations().getUserProfile().getId(),
                        facebook.userOperations().getUserProfile().getGender(), facebook.userOperations().getUserProfile().getName(),
                        facebook.userOperations().getUserProfile().getEmail(), facebook.userOperations().getUserProfile().getBirthday(),
                        facebook.userOperations().getUserProfile().getAbout(), facebook.userOperations().getUserProfile().getLocation().getName(),
                        facebook.userOperations().getUserProfile().getRelationshipStatus());
                profileSN.setDateOfBirthday(google.plusOperations().getGoogleProfile().getBirthday());
            } else if ("twitter".equals(userProfile.getFromProfileAbout())) {
                ProfileSN profileSN= new ProfileSN(Long.toString(twitter.userOperations().getUserProfile().getId()),
                        facebook.userOperations().getUserProfile().getGender(), twitter.userOperations().getUserProfile().getName(),
                        facebook.userOperations().getUserProfile().getEmail(), facebook.userOperations().getUserProfile().getBirthday(),
                        facebook.userOperations().getUserProfile().getAbout());
                profileSN.setDateOfBirthday(google.plusOperations().getGoogleProfile().getBirthday());
            } else if ("google".equals(userProfile.getFromProfileAbout())) {
                Person person = google.plusOperations().getGoogleProfile();
                ProfileSN profileSN= new ProfileSN(person.getId(),
                        person.getGender(), facebook.userOperations().getUserProfile().getName(),
                        facebook.userOperations().getUserProfile().getEmail(), person.getBirthday().toString(),
                        person.getAboutMe(), "Bucuresti,Romania",
                        "in_a_relationship".equals(person.getRelationshipStatus()) ? "In a relantionship" : "Single");
                profileSN.setDateOfBirthday(person.getBirthday());
                return profileSN;
            } else {
                ProfileSN profileSN= new ProfileSN(linkedIn.profileOperations().getProfileId(), facebook.userOperations().getUserProfile().getGender(), linkedIn.profileOperations().getUserProfileFull().getFirstName() +
                        " " + linkedIn.profileOperations().getUserProfileFull().getLastName(),
                        linkedIn.profileOperations().getUserProfileFull().getEmailAddress(),
                        facebook.userOperations().getUserProfile().getBirthday(),
                        linkedIn.profileOperations().getUserProfileFull().getSummary(),
                        linkedIn.profileOperations().getUserProfileFull().getLocation().getName(),
                        facebook.userOperations().getUserProfile().getRelationshipStatus());
                profileSN.setDateOfBirthday(google.plusOperations().getGoogleProfile().getBirthday());
            }
        }
        return new ProfileSN();
    }

    @Override
    public List<SNFriend> getFriendsProfile(User user) {
        List<SNFriend> snFriends = new ArrayList<SNFriend>();
        if (user != null) {
            UserProfile userProfile = userProfileRepository.findByUser(user);
            if (userProfile != null) {
                if ("facebook".equals(userProfile.getFromProfileFriends())) {
                } else if ("twitter".equals(userProfile.getFromProfileFriends())) {
                    List<SNFriend> snFriendList = twitterService.getFriends(null);
                    if (snFriendList.size() > 10) {
                        snFriends.addAll(twitterService.getFriends(null).subList(0, 10));
                    } else {
                        snFriends.addAll(twitterService.getFriends(null));
                    }
                } else if ("google".equals(userProfile.getFromProfileFriends())) {
                    List<SNFriend> snFriendList = googleService.findFriends(null);
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
        }
        return snFriends;
    }

    @Override
    public List<UsersDTO> findUsers(SearchUsersDTO searchUsersDTO, User user) {
        List<UsersDTO> usersDTOs = new ArrayList<UsersDTO>();
        BASE64Encoder encoder = new BASE64Encoder();
        PagingParameters pagingParameters = new PagingParameters(10, 10, new Long(1000), new Long(1000));

        if (facebookService.isConnectFacebook(user)) {
            PagedList<Reference> references = facebook.userOperations().search(searchUsersDTO.getName());
            if (references != null && references.size() > 0) {
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
        }
        if (twitterService.isConnectTwitter(user)) {
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
        }

        if (googleService.isConnectGoogle(user)) {
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
    public List<SNFeed> getFeed(User user) {
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        if (facebookService.isConnectFacebook(user)) {
            snFeeds.addAll(facebookService.getFeed());
        }
        if (twitterService.isConnectTwitter(user)) {
            snFeeds.addAll(twitterService.getFeed());
        }
        if (googleService.isConnectGoogle(user)) {
            snFeeds.addAll(googleService.findFeeds());
        }
//        try {
//            snFeeds.addAll(linkedinService.getFeed());
//        }catch(Exception e){
//        }
        Collections.sort(snFeeds);
        Collections.reverse(snFeeds);
        return snFeeds;
    }

    @Override
    public List<SNFeed> refreshFeed(String socialType,User user) {
        List<SNFeed> snFeeds = new ArrayList<SNFeed>();
        if ("facebook".equals(socialType)) {
            return facebookService.getFeed();
        } else if ("twitter".equals(socialType)) {
            return twitterService.getFeed();
        } else if ("linkedin".equals(socialType)) {
            return linkedinService.getFeed();
        } else if ("google".equals(socialType)) {
            return googleService.findFeeds();
        } else if ("myFavorites".equals(socialType)) {
            return favoritesFeeds();
        } else if ("myPosts".equals(socialType)) {
            snFeeds.addAll(facebookService.getMyPosts());
            snFeeds.addAll(twitterService.getMyPosts());
            snFeeds.addAll(googleService.getMyPosts());
        } else {
            getFeed(user);
        }
        Collections.sort(snFeeds);
        Collections.reverse(snFeeds);
        return snFeeds;
    }

    @Override
    public List<SNFeed> favoritesFeeds() {
        return twitterService.favoritesTweets();
    }

    @Override
    public SNFeed postPhoto(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, Boolean googleFlag, String message, User user) {
        if (facebookFlag) {
            facebook.feedOperations().post(facebook.userOperations().getUserProfile().getId(), message);
        }
        return null;
    }

}
