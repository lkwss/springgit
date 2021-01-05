package com.jk.controller;

import com.jk.pojo.Train;
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
    /**
    * @Author: Qzc
    * @Description:toqzcshow
    * @Date: 2021/1/5 0005 20:16
     * @param
    *  @Return: java.lang.String
    **/
    @RequestMapping("toqzcshow")
    public String toqzcshow(){
        return "qzcshow";
    }
    /**
    * @Author: Qzc
    * @Description:toqzcadd
    * @Date: 2021/1/5 0005 20:16
     * @param
    *  @Return: java.lang.String
    **/
    @RequestMapping("toqzcadd")
    public String toqzcadd(){
        return "qzcadd";
    }

    /**
    * @Author: Qzc
    * @Description:qzcfindtable
    * @Date: 2021/1/5 0005 20:16
     * @param offset
     * @param limit
     * @param train
    *  @Return: java.util.HashMap<java.lang.String,java.lang.Object>
    **/
    @RequestMapping("qzcfindtable")
    @ResponseBody
    public HashMap<String,Object> qzcfindtable(Integer offset, Integer limit, Train train){
        return testService.qzcfindtable(offset,limit,train);
    }
    /**
    * @Author: Qzc
    * @Description:qzcdell
    * @Date: 2021/1/5 0005 20:22
     * @param id
    *  @Return: void
    **/
    @RequestMapping("qzcdell")
    @ResponseBody
    public void qzcdell(Integer id){
        testService.qzcdell(id);
    }
    /**
    * @Author: Qzc
    * @Description:qzcadd
    * @Date: 2021/1/5 0005 20:28
     * @param train
    *  @Return: void
    **/
    @RequestMapping("qzcadd")
    @ResponseBody
    public void qzcadd(Train train){
        testService.qzcadd(train);
    }

    /**
    * @Author: Qzc
    * @Description:qzcgetbyid
    * @Date: 2021/1/5 0005 20:29
     * @param id
    *  @Return: com.jk.pojo.Train
    **/
    @RequestMapping("qzcgetbyid")
    @ResponseBody
    public Train qzcgetbyid(Integer id){
        return testService.qzcgetbyid(id);
    }

}
