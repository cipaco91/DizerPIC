package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;

import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */
public interface GoogleService {

    boolean isConnectGoogle(User user);

    List<SNFriend> findFriends(String name,Integer age1, Integer age2);

    List<SNFeed> findFeeds();

    List<SNFeed> getMyPosts();
}
