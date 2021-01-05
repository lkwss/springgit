package com.jk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jk.dao.EsDao;
import com.jk.dao.TestDao;
import com.jk.pojo.Train;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    private ElasticsearchTemplate estemplate;

    @Autowired
    private EsDao esDao;

    @Override
    public HashMap<String, Object> qzcfindtable(Integer offset, Integer limit, Train train) {

        List<Train> list = new ArrayList<>();

        Client client = estemplate.getClient();

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

}
