package com.ruwork.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "ruwork", type = "Customer")
public class Customer {


   private  @Id String id;

   private  String name;

   private String cellphone;

   private String type;

   private String desc;

   private String saleCode;

}
