package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.ProfileSN;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import com.yesnault.sag.util.SearchUsersDTO;
import com.yesnault.sag.util.UsersDTO;

import java.util.List;

/**
 * Created by Ciprian on 3/22/2015.
 */
public interface SocialNetworkService {

    void updateStatus(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String message);

    void searchFriends(Boolean facebookFlag, Boolean twitterFlag, Boolean linkedinFlag, String search);

    String profileImageURL();

    ProfileSN getProfileUser();

    List<SNFriend> getFriendsProfile();

    List<UsersDTO> findUsers(SearchUsersDTO searchUsersDTO);

    List<SNFeed>getFeed();
}
