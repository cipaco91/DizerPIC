package com.yesnault.sag.interfaces;

import java.util.Date;

/**
 * Created by CParaschivescu on 6/9/2015.
 */
public interface UtilService {

    Integer getAgeByBirthday(Date birthday);

    Boolean ageVal( Integer age1, Integer age2);
}
