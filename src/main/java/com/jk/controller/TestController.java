package com.jk.controller;

import com.jk.pojo.Train;
import com.jk.pojo.CarBean;
import com.jk.pojo.EmpBean;
import com.jk.pojo.StudentBean;
import com.jk.pojo.MusicBean;
import com.jk.pojo.*;
import com.jk.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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


    @RequestMapping("fwjselect")
    @ResponseBody
    public HashMap<String,Object> fwjselect(Integer page,Integer rows) {
        return testService.fwjselect(page, rows);
    }
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

    @RequestMapping("fwjadd")
    @ResponseBody
    public void fwjadd(StudentBean bea){
        testService.fwjadd(bea);
    }

    @RequestMapping("fwjselectid")
    @ResponseBody
    public StudentBean fwjselectid(Integer id){
        return testService.fwjselectid(id);
    }
    @RequestMapping("fwjcha")
    public String fwjcha(){
        return "showfwj";
    }
    @RequestMapping("fwjzeng")
    public String fwjzeng(){
        return "fwjadd";
    }

    @RequestMapping("fwjdelete")
    @ResponseBody
    public void fwjdelete(Integer id) {
        testService.fwjdelete(id);
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

    /**
     *
     * @return
     */
    @RequestMapping("toMain")
    public String toMain(){
        return "emp/main";
    }
    /**
     *编辑
     */
    @RequestMapping("toEditPage")
    public String toEditPage(){
        return "emp/editemp";
    }
    /**
     *
     * @return
     */
    @RequestMapping("toAddPage")
    public String toAddPage(){
        return "emp/addemp";
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("findEmpById")
    @ResponseBody
    public EmpBean findEmpById(Integer id){
        return testService.findEmpById(id);
    }
    /**
     * 删除
     * @param id
     */
    @RequestMapping("delEmpById")
    @ResponseBody
    public void delEmpById(Integer id){
        testService.delEmpById(id);
    }
    /**
     *新增
     * @param empBean
     */
    @RequestMapping("saveEmp")
    @ResponseBody
    public void saveEmp(EmpBean empBean){
        testService.saveEmp(empBean);
    }
    /**
     *查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("findEmp")
    @ResponseBody
    public Map findEmp(Integer page,Integer rows){
        return testService.findEmp(page,rows);
    }
}
