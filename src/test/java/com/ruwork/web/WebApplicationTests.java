package com.ruwork.web;

import com.ruwork.web.dal.CustomerDAO;
import com.ruwork.web.dal.CustomerRepository;
import com.ruwork.web.dal.SaleDAO;
import com.ruwork.web.dal.SaleRepository;
import com.ruwork.web.entity.Customer;
import com.ruwork.web.entity.Sale;
import com.ruwork.web.model.CustomerModel;
import com.ruwork.web.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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

	@Autowired
	private CustomerService customerService;



	@Test
	void esConnectionTest(){
//		elasticsearchOperations.deleteIndex("ruwork");
//		Assert.isTrue(!elasticsearchOperations.indexExists("ruwork"),"此时索引不存在");
		Sale sale = new Sale();
		sale.setId("1");
		sale.setName("zhangsasn");
		sale.setCellphone("123000");
		sale.setIdentity("123231203");
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(sale.getId())
				.withObject(sale)
				.build();
		elasticsearchOperations.createIndex(Sale.class);
		elasticsearchOperations.index(indexQuery);
		Assert.isTrue(elasticsearchOperations.indexExists(Sale.class),"此时索引存在");
		Assert.isTrue(saleRepository.count()>0,"sale库有数据");

		Sale sale1 = saleDAO.querySaleByPhoneAndIdentity("123000","123231203");
		Assert.notNull(sale1,sale1.getName());

		Assert.isTrue(customerRepository.count()==0,"初始化时没有值");

		Customer customer = new Customer();
		customer.setId("1");
		customer.setCellphone("18600404539");
		customer.setName("张三");
		customer.setSaleCode("123000");
		Customer  resp = customerDAO.save(customer);
		Assert.notNull(resp,resp.getName());

	}


	@Test
	public void CustomerServiceTest(){

		Page<CustomerModel> page =  customerService.queryCustomerBySale("123000",1,10);

		Assert.isTrue(!page.isEmpty(),"有一条记录");
		Assert.notNull(page.get().findFirst(),"记录不为空");

	}

}
