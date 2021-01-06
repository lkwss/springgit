package com.jk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Document(indexName = "train",type = "20065")
public class Train {
    @Id
    private Integer id;

    private String trainname;

    private String tname;

    @Field(type = FieldType.Text,copyTo = "detail")
    private String start;
    @Field(type = FieldType.Text,copyTo = "detail")
    private String end;

    private Double price;

    private String info;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    @Transient
    private Double startprice;

    @Transient
    private Double endprice;

    @Transient
    private String strandend;


}
