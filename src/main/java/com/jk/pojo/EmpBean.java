package com.jk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "emps",type = "emp")
public class EmpBean {

    @Id
    private Integer id;
    private String empName;
    private Double pay;
    private String motto;
    private String sex;

}
