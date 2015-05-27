package com.yesnault.sag.interfaces;

import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;

import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */
public interface GoogleService {

    boolean isConnectGoogle();

    List<SNFriend> findFriends();

    List<SNFeed> findFeeds();
}
