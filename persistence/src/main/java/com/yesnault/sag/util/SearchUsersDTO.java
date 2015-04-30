package com.yesnault.sag.util;

import java.io.Serializable;

/**
 * Created by CParaschivescu on 4/30/2015.
 */
public class SearchUsersDTO implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
