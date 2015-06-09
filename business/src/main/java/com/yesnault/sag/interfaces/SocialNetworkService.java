package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.ProfileSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface SocialNetworkService {

    SNFeed updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, Boolean googleFlag, String message, User user);

    void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag,Boolean googleFlag, String search);

    String profileImageURL(User user);

    ProfileSN getProfileUser(User user);

    List<SNFriend> getFriendsProfile(User user);

    List<UsersDTO> findUsers(SearchUsersDTO searchUsersDTO, User user);

    List<SNFeed> getFeed(User user);

    List<SNFeed> refreshFeed(String socialType,User user);

    List<SNFeed> favoritesFeeds();

    SNFeed postPhoto(Boolean facebookFlag, Boolean twitterFlag,
                     Boolean linkedinFlag, Boolean googleFlag, String message, User user);

}
