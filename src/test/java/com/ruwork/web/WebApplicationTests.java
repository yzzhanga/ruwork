package com.ruwork.web;

import com.ruwork.web.dal.CustomerDAO;
import com.ruwork.web.dal.CustomerRepository;
import com.ruwork.web.dal.SaleDAO;
import com.ruwork.web.dal.SaleRepository;
import com.ruwork.web.entity.Customer;
import com.ruwork.web.entity.Sale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.util.Assert;

@SpringBootTest
class WebApplicationTests {

	@Qualifier("elasticsearchOperations")
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private SaleDAO saleDAO;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerDAO customerDAO;


	@Test
	void contextLoads() {
	}


	@Test
	void esConnectionTest(){
		elasticsearchOperations.deleteIndex("ruwork");
		Assert.isTrue(!elasticsearchOperations.indexExists("ruwork"),"此时索引不存在");
		Sale sale = new Sale();
		sale.setId("1");
		sale.setName("zhangsasn");
		sale.setCellphone("123000");
		sale.setIdentity("123231203");
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(sale.getId())
				.withObject(sale)
				.build();
		elasticsearchOperations.index(indexQuery);
		Assert.isTrue(elasticsearchOperations.indexExists("ruwork"),"此时索引存在");

	}

	@Test
	void esSaleDao(){

		Assert.isTrue(saleRepository.count()>0,"sale库有数据");

		Sale sale = saleDAO.querySaleByPhoneAndIdentity("123000","123231203");
		Assert.notNull(sale,sale.getName());

		Assert.isTrue(customerRepository.count()==0,"初始化时没有值");

		Customer customer = new Customer();
		customer.setId("1");
		customer.setCellphone("18600404539");
		customer.setName("张三");
		customer.setSaleCode("123000");
		Customer  resp = customerRepository.save(customer);
		Assert.notNull(resp,resp.getName());
	}
}
