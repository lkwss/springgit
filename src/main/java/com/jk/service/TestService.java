package com.jk.service;

import com.jk.pojo.Train;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:39
 */
public interface TestService {
    HashMap<String, Object> qzcfindtable(Integer offset, Integer limit, Train train);

    void qzcdell(Integer id);

    void qzcadd(Train train);

    Train qzcgetbyid(Integer id);
}
