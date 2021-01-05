package com.jk.service;

import com.jk.pojo.CarBean;
import com.jk.pojo.StudentBean;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:39
 */
public interface TestService {
    HashMap<String, Object> fwjselect(Integer page, Integer rows);
    HashMap<String, Object> findCar(int page, int rows);

    void saveCar(CarBean carBean);

    CarBean findCarById(Integer carId);

    void delCar(Integer carId);

    void fwjadd(StudentBean bea);

    StudentBean fwjselectid(Integer id);

    void fwjdelete(Integer id);
}
