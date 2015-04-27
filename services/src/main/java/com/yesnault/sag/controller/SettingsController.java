package com.yesnault.sag.controller;

import com.yesnault.sag.model.User;
import com.yesnault.sag.util.WizzardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CParaschivescu on 4/27/2015.
 */
@Controller
public class SettingsController {

    @RequestMapping(value = "/wizzardDTO", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    WizzardDTO getWizzardDTO() {
        return new WizzardDTO();
    }

    @RequestMapping(value = "/finishWizzardProfile", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Boolean finishWizzardProfile(@RequestBody WizzardDTO wizzardDTO) {
        System.out.println(wizzardDTO.getFirstName() + " " + wizzardDTO.getLastName());
        return true;
    }


}
