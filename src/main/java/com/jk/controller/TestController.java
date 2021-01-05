package com.jk.controller;

import com.jk.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:38
 */
@Controller
@RequestMapping("test")
public class TestController {


    @Autowired
    private TestService testService;

    @RequestMapping("fwjselect")
    @ResponseBody
    public HashMap<String,Object> fwjselect(Integer page,Integer rows){
        return testService.fwjselect(page,rows);
    }
}
