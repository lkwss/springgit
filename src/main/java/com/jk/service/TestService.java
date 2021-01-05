package com.jk.service;

import com.jk.pojo.CarBean;
import com.jk.pojo.MusicBean;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:39
 */
public interface TestService {
    HashMap<String, Object> findCar(int page, int rows);

    void saveCar(CarBean carBean);

    CarBean findCarById(Integer carId);

    void delCar(Integer carId);

    HashMap<String, Object> initmusic(Integer page, Integer rows);

    void addmusic(MusicBean musicBean);

    MusicBean findmusicById(Integer id);

    void delmusic(Integer id);
}
