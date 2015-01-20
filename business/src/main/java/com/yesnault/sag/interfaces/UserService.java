package com.yesnault.sag.interfaces;

import com.yesnault.sag.model.User;

import java.util.List;

/**
 * Created by CParaschivescu on 1/20/2015.
 */
public interface UserService {

    List<User> findByLastname(String lastname);
}
