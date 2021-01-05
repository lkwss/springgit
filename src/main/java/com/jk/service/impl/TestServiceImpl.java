package com.jk.service.impl;

import com.jk.dao.TestDao;
import com.jk.pojo.StudentBean;
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
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }
}
