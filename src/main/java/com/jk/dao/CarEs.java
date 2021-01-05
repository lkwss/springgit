package com.jk.dao;

import com.jk.pojo.CarBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 20:02
 */
public interface CarEs extends ElasticsearchRepository<CarBean,Integer> {
}
