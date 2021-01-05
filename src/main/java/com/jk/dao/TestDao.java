package com.jk.dao;

import com.jk.pojo.Train;
import com.jk.pojo.CarBean;
import com.jk.pojo.EmpBean;
import com.jk.pojo.MusicBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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

    @Insert("insert into t_emp(empName,pay,motto,sex) values(#{empName},#{pay},#{motto},#{sex}) ")
    void saveEmp(EmpBean empBean);

    @Delete("delete from t_emp where id = #{id}")
    void delEmpById(Integer id);

    void updCar(CarBean carBean);

    @Update("update t_emp set empName = #{empName},pay = #{pay},motto = #{motto},sex = #{sex} where id = #{id}")
    void updateEmp(EmpBean empBean);

    void addmusic(MusicBean musicBean);

    void updmusic(MusicBean musicBean);

    void delmusic(Integer id);
}
