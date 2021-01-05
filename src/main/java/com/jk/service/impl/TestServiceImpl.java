package com.jk.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.jk.dao.CarEs;
import com.jk.dao.MusicEs;
import com.jk.dao.TestDao;
import com.jk.pojo.CarBean;
import com.jk.pojo.MusicBean;
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
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private MusicEs musicEs;

    @Autowired
    private ElasticsearchTemplate template;

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
            list.add(carBean);
        }
        //获取总条数：
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void saveCar(CarBean carBean) {
        if (carBean.getCarId()!=null){
            testDao.updCar(carBean);
        }else {
            testDao.saveCar(carBean);
        }
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
    public HashMap<String, Object> initmusic(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<MusicBean> list = new ArrayList<>();
        Client client = template.getClient();
        SearchRequestBuilder search  = client.prepareSearch("t_music").setTypes("music");
        BoolQueryBuilder bool = new BoolQueryBuilder();
        //分页
        search.setFrom((page-1)*rows);//开始位置
        search.setSize(rows);//没有条数
        SearchResponse searchResponse = search.get();

        SearchHits hits = searchResponse.getHits();

        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            String str = next.getSourceAsString();
            //把字符串转换成javabean对象
            MusicBean bookBean = JSONObject.parseObject(str, MusicBean.class);
            list.add(bookBean);
        }

        //获取总条数：
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void addmusic(MusicBean musicBean) {
        if(musicBean.getId()==null){
            testDao.addmusic(musicBean);
        }else{
            testDao.updmusic(musicBean);
        }
        musicEs.save(musicBean);
    }

    @Override
    public MusicBean findmusicById(Integer id) {
        Optional<MusicBean> byId =musicEs.findById(id);
        MusicBean musicBean=byId.get();
        return musicBean;
    }

    @Override
    public void delmusic(Integer id) {
        musicEs.deleteById(id);
        testDao.delmusic(id);
    }
}
