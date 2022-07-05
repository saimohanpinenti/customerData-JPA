package com.mohan.customer.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mohan.customer.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
//	assignment fidByemailAndName
//	find by partial email.
//	give a set of ids 
	List<Customer>findByEmailAndName(String email,String name);
	List<Customer>findByEmailLike(String email);
	List<Customer>findByIdIn(Integer[]ids);
	
	@Query("from Customer")
	List<Customer>findAllStudents(Pageable page);
	
	@Modifying
	@Query("update Customer SET email =:email where id=:id ")
	void updateEmailByID(@Param("email")String email,@Param("id") int id);

}
