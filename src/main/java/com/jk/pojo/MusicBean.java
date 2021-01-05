package com.jk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "t_music",type="music")
public class MusicBean {
    @Id
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "ik_smart",copyTo = "detail")
    private String musicname;
    private String author;
    private String launchtime;
    private Double price;
    private String info;
    @Transient
    private String mindate;
    @Transient
    private String maxdate;
}
