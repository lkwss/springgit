package com.jk.dao;

import com.jk.pojo.Train;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface EsDao extends ElasticsearchCrudRepository<Train, Integer> {

}
