package com.jk.dao;

import com.jk.pojo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:39
 */
@Mapper
@Repository
public interface TestDao {


    void deleteByPrimaryKey(Integer id);

    Train selectByPrimaryKey(Integer id);

    void insert(Train train);

    void updateByPrimaryKeySelective(Train train);
    void saveCar(CarBean carBean);

    @Delete("delete from t_car where carId = #{carId}")
    void delCar(Integer carId);

    void fwjadd(StudentBean bea);

    StudentBean fwjselectid(Integer id);

    void fwjupdate(StudentBean bea);

    void fwjdelete(Integer id);
    void updCar(CarBean carBean);

    void addmusic(MusicBean musicBean);

    void updmusic(MusicBean musicBean);

    void delmusic(Integer id);

    List<TreeBean> findTree(int pid);
}
