package com.jk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2021/1/5
 * Time: 19:47
 */
@Data
@Document(indexName = "car",type = "t_car")
public class CarBean {

    @Id
    private Integer carId;
    private String carName;
    private String typeName;
    private Integer carPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String carDate;
}
