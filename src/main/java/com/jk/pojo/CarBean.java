package com.jk.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:47
 */
@Document(indexName = "car",type = "t_car")
public class CarBean {

    @Id
    private Integer carId;
    private String carName;
    private String typeName;
    private Integer carPrice;
    private String carDate;
}
