package com.mohan.customer;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;

import com.mohan.customer.entities.Customer;
import com.mohan.customer.repo.CustomerRepository;

@SpringBootTest
class CustomerDataApplicationTests {

	@Autowired
	CustomerRepository repo;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void addCustomerTest() {

		Customer customer = new Customer();
//		customer.setId(1);
		customer.setName("sai");
		customer.setEmail("sai@gmail.com");
		repo.save(customer);
	}
	
	@Test
	public void FindCustomer() {
		Customer customer = repo.findById(1).get();
		Assertions.assertEquals(customer.getId(), 1);
	}

	@Test
	public void DeleteCustomer() {
		Customer customer = repo.findById(1).get();
		if (customer != null) {
			repo.deleteById(customer.getId());;
		}
	}
	
	@Test
	public void getCoustomers() {
		List<Customer> customers = repo.findByEmailAndName("sai@gmail.com", "sai");
		customers.stream().forEach(customer ->System.out.println(customer.toString()));
	}
	@Test
	public void getCoustomersByemail() {
		List<Customer> customers = repo.findByEmailLike("%sai%");
		customers.stream().forEach(customer ->System.out.println(customer.toString()));
	}
	
	@Test
	public void getCoustomersByIds() {
		Integer [] ids = {1,2};
		List<Customer> customers = repo.findByIdIn(ids);
		customers.stream().forEach(customer ->System.out.println(customer.toString()));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void setEmailById() {
		repo.updateEmailByID("vishal@gmail", 1);
	}
	
	@Test
	public void findBypaging() {
		repo.findAllStudents(PageRequest.of(0, 1, Direction.DESC, "id"));
	}
}
