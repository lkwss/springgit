package com.jk.controller;

import com.jk.pojo.CarBean;
import com.jk.pojo.MusicBean;
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

    @RequestMapping("carSave")
    public String carSave(){
        return "carSave";
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
    /**
     * @Author: lkw
     * @Description:查询
     * @Date: 2021/1/5 20:46
      * @param page
     * @param rows
     * @Return: java.util.HashMap<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("initmusic")
    @ResponseBody
    public HashMap<String, Object> initmusic(Integer page, Integer rows){
        return testService.initmusic(page, rows);
    }
    /**
     * @Author: lkw
     * @Description:新增音乐
     * @Date: 2021/1/5 20:47
      * @param musicBean
     * @Return: void
     **/
    @RequestMapping("addmusic")
    @ResponseBody
    public void addmusic(MusicBean musicBean){
        testService.addmusic(musicBean);
    }
    /**
     * @Author: lkw
     * @Description:回显
     * @Date: 2021/1/5 20:55
      * @param id
     * @Return: com.jk.pojo.MusicBean
     **/
    @RequestMapping("findmusicById")
    @ResponseBody
    public MusicBean findmusicById(Integer id){
        return testService.findmusicById(id);
    }
    /**
     * @Author: lkw
     * @Description:删除
     * @Date: 2021/1/5 21:04
      * @param id
     * @Return: void
     **/
    @RequestMapping("delmusic")
    @ResponseBody
    public void delmusic(Integer id){
        testService.delmusic(id);
    }
    @RequestMapping("goshow")
    public String goshow(){
        return "showlkw";
    }
    @RequestMapping("toadd")
    public String goadd(){
        return "addgoods";
    }
}
