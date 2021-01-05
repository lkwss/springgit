package com.jk.controller;

import com.jk.pojo.CarBean;
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

    @RequestMapping("lwShow")
    public String lwShow(){
        return "lwShow";
    }

    @RequestMapping("findCar")
    @ResponseBody
    public HashMap<String,Object> findCar(int page,int rows){
        return testService.findCar(page,rows);
    }

    @RequestMapping("saveCar")
    @ResponseBody
    public void saveCar(CarBean carBean){
        testService.saveCar(carBean);
    }

    @RequestMapping("findCarById")
    @ResponseBody
    public CarBean findCarById(Integer carId){
        return testService.findCarById(carId);
    }

    @RequestMapping("delCar")
    @ResponseBody
    public void delCar(Integer carId){
        testService.delCar(carId);
    }
}
