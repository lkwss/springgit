package com.jk.dao;

import com.jk.pojo.EmpBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmpEs extends ElasticsearchRepository<EmpBean,Integer> {
}
