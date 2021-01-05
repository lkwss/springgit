package com.jk.dao;

import com.jk.pojo.Train;
import org.apache.ibatis.annotations.Mapper;
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
}
