package com.jk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jk.dao.CarEs;
import com.jk.dao.Stude;
import com.jk.dao.TestDao;
import com.jk.pojo.StudentBean;
import com.jk.pojo.CarBean;
import com.jk.service.TestService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:40
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    @Autowired
    private CarEs carEs;
    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private Stude stude;
    @Autowired
    private ElasticsearchTemplate templatefwj;
    @Override
    public HashMap<String, Object> fwjselect(Integer page, Integer rows) {
        List<StudentBean> list=new ArrayList<>();
        HashMap<String,Object> map= new HashMap<>();
        Client client = templatefwj.getClient();
        SearchRequestBuilder car = client.prepareSearch("student").setTypes("0105");
        car.setFrom((page-1)*rows);
        car.setSize(rows);
        SearchResponse searchResponse = car.get();
        SearchHits hits = searchResponse.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            String str = next.getSourceAsString();
            StudentBean student = JSONObject.parseObject(str, StudentBean.class);
        }
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
}
    @Override
    public HashMap<String, Object> findCar(int page, int rows) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        List<CarBean> list = new ArrayList<>();

        //1、获取es客户端对象
        Client client = template.getClient();
        //2、创建查询对象：设置索引、类型
        SearchRequestBuilder search = client.prepareSearch("car")//索引、数据库
                .setTypes("t_car");//类型、表

        //分页
        search.setFrom((page-1)*rows);//开始位置
        search.setSize(rows);//没有条数

        //3、执行、获取查询结果
        SearchResponse searchResponse = search.get();

        SearchHits hits = searchResponse.getHits();

        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            String str = next.getSourceAsString();
            //把字符串转换成javabean对象
            CarBean carBean = JSONObject.parseObject(str, CarBean.class);
        }
        //获取总条数：
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void saveCar(CarBean carBean) {
        testDao.saveCar(carBean);
        carEs.save(carBean);
    }

    @Override
    public CarBean findCarById(Integer carId) {
        Optional<CarBean> byId = carEs.findById(carId);
        CarBean carBean = byId.get();
        return carBean;
    }

    @Override
    public void delCar(Integer carId) {
        testDao.delCar(carId);
        carEs.deleteById(carId);
    }

    @Override
    public void fwjadd(StudentBean bea) {
        if (bea.getId()==null){
            testDao.fwjadd(bea);
        }else{
            testDao.fwjupdate(bea);
        }
        stude.save(bea);
    }

    @Override
    public StudentBean fwjselectid(Integer id) {
        return testDao.fwjselectid(id);
    }

    @Override
    public void fwjdelete(Integer id) {
        stude.deleteById(id);
        testDao.fwjdelete(id);
    }
}
