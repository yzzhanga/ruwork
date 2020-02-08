package com.ruwork.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "sale", type = "doc")
public class Sale {
    @Id
    private String id;
    private String name;
    private String saleCode;
    private String cellphone;
    private String identity;
}
