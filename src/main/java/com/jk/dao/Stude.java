package com.jk.dao;

import com.jk.pojo.StudentBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface Stude extends ElasticsearchRepository<StudentBean,Integer> {
}
