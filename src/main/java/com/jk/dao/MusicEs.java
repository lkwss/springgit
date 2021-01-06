package com.jk.dao;

import com.jk.pojo.MusicBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MusicEs extends ElasticsearchRepository<MusicBean,Integer> {
}
