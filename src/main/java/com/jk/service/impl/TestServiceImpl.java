package com.jk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jk.dao.EsDao;
import com.alibaba.fastjson.JSONObject;
import com.jk.dao.CarEs;
<<<<<<< HEAD
import com.jk.dao.EmpEs;
=======
import com.jk.dao.Stude;
import com.jk.dao.TestDao;
import com.jk.pojo.StudentBean;
>>>>>>> fd57b1eddbc0f301779daa2b71f2b7003505f403
import com.jk.dao.MusicEs;
import com.jk.dao.TestDao;
import com.jk.pojo.Train;
import com.jk.pojo.CarBean;
import com.jk.pojo.EmpBean;
import com.jk.pojo.MusicBean;
import com.jk.service.TestService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    private EsDao esDao;

    @Autowired
    private CarEs carEs;
    @Autowired
    private EmpEs empEs;

    @Autowired
    private MusicEs musicEs;

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
        Client client = template.getClient();
        SearchRequestBuilder car = client.prepareSearch("student").setTypes("0105");
        car.setFrom((page-1)*rows);
        car.setSize(rows);
        SearchResponse searchResponse = car.get();
        SearchHits hits = searchResponse.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            String str = next.getSourceAsString();
            StudentBean studentBean = JSONObject.parseObject(str, StudentBean.class);
            list.add(studentBean);
        }
        long total = hits.getTotalHits();
        map.put("total",total);
        map.put("rows",list);
        return map;
}
    @Override
    public HashMap<String, Object> qzcfindtable(Integer offset, Integer limit, Train train) {

        List<Train> list = new ArrayList<>();

        Client client = template.getClient();

        SearchRequestBuilder search = client.prepareSearch("train")//索引、数据库
                .setTypes("20065");//类型、表

        BoolQueryBuilder bool = new BoolQueryBuilder();
        if(!StringUtils.isEmpty(train.getStrandend())){
            bool.must(QueryBuilders.matchQuery("detail",train.getStrandend()));
            /*bool.must(QueryBuilders.matchQuery("start",train.getStrandend()));
            bool.must(QueryBuilders.matchQuery("end",train.getStrandend()));*/
        }

        //价格的区间查询
        RangeQueryBuilder price = QueryBuilders.rangeQuery("price");
        if(train.getStartprice() !=null){
            price.gte(train.getStartprice());
        }

        if(train.getEndprice()!=null){
            price.lte(train.getEndprice());
        }
        if(train.getStartprice()!=null || train.getEndprice()!=null){
            bool.must(price);
        }

        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("start");//名称高亮
        highlightBuilder.field("end");//简介高亮
        // <font color="red"></font>
        highlightBuilder.preTags("<font color=\"red\">");//前缀
        highlightBuilder.postTags("</font>");//后缀
        search.highlighter(highlightBuilder);


        search.setQuery(bool);
        //排序: 先价格升序、id降序
        search.addSort("time", SortOrder.ASC);
        //分页
        search.setFrom(offset);//开始位置
        search.setSize(limit);//没有条数

        //3、执行、获取查询结果
        SearchResponse searchResponse = search.get();

        SearchHits hits = searchResponse.getHits();

        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            String str = next.getSourceAsString();
            Train trainBean = JSONObject.parseObject(str, Train.class);

            Map<String, HighlightField> highlightFields = next.getHighlightFields();
            HighlightField start = highlightFields.get("start");
            if(start!=null){
                String start2 = start.getFragments()[0].toString();
                trainBean.setStart(start2);
            }
            HighlightField end = highlightFields.get("end");
            if(end!=null){
                String end2 = end.getFragments()[0].toString();
                trainBean.setEnd(end2);
            }

            list.add(trainBean);
        }
        long total = hits.getTotalHits();
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void qzcdell(Integer id) {
        esDao.deleteById(id);
        testDao.deleteByPrimaryKey(id);
    }

    @Override
    public Train qzcgetbyid(Integer id) {
        return testDao.selectByPrimaryKey(id);
    }

    @Override
    public void qzcadd(Train train) {
        if (train.getId()==null){
            double floor = Math.floor(Math.random() * 8999 + 10000);
            train.setId((int) floor);
            testDao.insert(train);
        }else {
            testDao.updateByPrimaryKeySelective(train);
        }
        esDao.save(train);
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
<<<<<<< HEAD
    public Map<String,Object> findEmp(Integer page, Integer rows) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<EmpBean> list = new ArrayList<>();

        //1、获取es客户端对象
        Client client = template.getClient();
        //2、创建查询对象：设置索引、类型
        SearchRequestBuilder search = client.prepareSearch("emps")//索引、数据库
                .setTypes("emp");//类型、表

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
            EmpBean empBean = JSONObject.parseObject(str, EmpBean.class);
            list.add(empBean);
        }
        //获取总条数：
        long total = hits.getTotalHits();
        result.put("total",total);
        result.put("rows",list);
        return result;
    }

    @Override
    public void saveEmp(EmpBean empBean) {
        if (empBean.getId()!=null){
            testDao.updateEmp(empBean);
        }else {
            testDao.saveEmp(empBean);
        }
        empEs.save(empBean);
    }

    @Override
    public void delEmpById(Integer id) {
        empEs.deleteById(id);
        testDao.delEmpById(id);
    }

    @Override
    public EmpBean findEmpById(Integer id) {
        Optional<EmpBean> esById = empEs.findById(id);
        EmpBean empBean = esById.get();
        return empBean;
    }

    @Override
=======
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
>>>>>>> fd57b1eddbc0f301779daa2b71f2b7003505f403
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
