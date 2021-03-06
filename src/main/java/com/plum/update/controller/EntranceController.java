package com.plum.update.controller;

import com.plum.update.dto.UpdateIntroDTO;
import com.plum.update.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by G_dragon on 2015/12/29.
 */
@Controller
public class EntranceController {

    @Autowired
    private IUpdateService updateService;

    @RequestMapping("index")
    public String getIndex(){

        return "index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object listUpdateFile(){

        List<UpdateIntroDTO> updateIntroDTOList = updateService.listUpdateFile();

        return updateIntroDTOList;
    }
}
