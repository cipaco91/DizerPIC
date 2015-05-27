package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.GoogleService;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by CParaschivescu on 5/27/2015.
 */
@Service(value = "googleServiceImpl")
@Transactional
public class GoogleServiceImpl implements GoogleService{

    @Inject
    private Google google;

    @Override
    public boolean isConnectGoogle() {
        try{
            return google.plusOperations()!=null;
        }catch (Exception e){
            return false;
        }
    }
}
