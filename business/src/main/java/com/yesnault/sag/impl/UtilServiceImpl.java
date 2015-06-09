package com.yesnault.sag.impl;

import com.yesnault.sag.interfaces.FacebookService;
import com.yesnault.sag.interfaces.GoogleService;
import com.yesnault.sag.interfaces.UserService;
import com.yesnault.sag.interfaces.UtilService;
import com.yesnault.sag.model.User;
import com.yesnault.sag.model.UserProfile;
import com.yesnault.sag.pojo.CommentFeed;
import com.yesnault.sag.pojo.SNFeed;
import com.yesnault.sag.pojo.SNFriend;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by CParaschivescu on 5/27/2015.
 */
@Service(value = "utilServiceImpl")
@Transactional
public class UtilServiceImpl implements UtilService {

    @Inject
    Facebook facebook;


    @Override
    public Integer getAgeByBirthday(Date birthday) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        return year - cal.get(Calendar.YEAR);
    }

    @Override
    public Boolean ageVal(Integer age1, Integer age2) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(facebook.userOperations().getUserProfile().getBirthday());
            Integer currentAge = getAgeByBirthday(date);
            if (currentAge > age1 && age2 > currentAge) return true;
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
