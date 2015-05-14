package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;
import com.yesnault.sag.util.WizzardDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by CParaschivescu on 1/20/2015.
 */
public interface UserService {

    List<User> findByLastname(String lastname);

    User findByUsernameAndPassword(String username,String password);

    Boolean saveUserWizzardProfile(WizzardDTO wizzardDTO);

    void logoutFromSocialNetworks(User user);

    String login(String username, String password, HttpServletRequest request);

    String signUp(String username, String password,String firstName,String lastName);
}
