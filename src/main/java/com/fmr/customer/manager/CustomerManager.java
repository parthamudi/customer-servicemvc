package com.fmr.customer.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fmr.customer.mapper.CustomerMapper;
import com.fmr.customer.model.Customer;

@Component
public class CustomerManager {
	 private CustomerMapper customerMapper;
	 
	 public CustomerManager(CustomerMapper customerMapper) {
		 this.customerMapper=customerMapper;
	 }
	 public List<Customer> findAll(){
		 return customerMapper.findAll();
	 }
	 public Customer getCustomerBySSN( long ssn) {
		 return customerMapper.getCustomerBySSN(ssn);
	 }
	    public int updateBySSN(Customer customer) {
	    	return customerMapper.updateBySSN(customer);
	    }
	    public int deleteBySSN(long ssn) {
	    	return customerMapper.deleteBySSN(ssn);
	    }
	    public void insert(Customer customer) {
	    	customerMapper.insert(customer);
	    }
}
