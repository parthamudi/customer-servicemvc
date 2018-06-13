package com.fmr.customer.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fmr.customer.model.Customer;

import java.util.List;

@Mapper
public interface CustomerMapper {
    public List<Customer> findAll();
    public Customer getCustomerBySSN(@Param("ssn") long ssn);
    public int updateBySSN(@Param("customer") Customer customer);
    public int deleteBySSN(@Param("ssn") long ssn);
    void insert(@Param("customer") Customer customer);
 
}
