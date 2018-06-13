package com.fmr.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fmr.customer.dto.CustomerDTO;
import com.fmr.customer.manager.CustomerManager;
import com.fmr.customer.model.Customer;



@RestController
@RequestMapping("/v1/customers")
public class CustomerServiceController {
@Autowired
    private CustomerManager customerManager;

   private static ModelMapper mapper=new ModelMapper();
    
    @RequestMapping(value = "/getAllCustomers/", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> listAllCustomers() {
        List<Customer> customers = customerManager.findAll();
        List<CustomerDTO> customersdto=(List<CustomerDTO>) mapper.map(customers,CustomerDTO.class);
        if (customers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CustomerDTO>>(customersdto, HttpStatus.OK);
    }
 
 
    @RequestMapping(value = "/getCustomer/{ssn}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable("ssn") long ssn) {
        Customer customer = customerManager.getCustomerBySSN(ssn);
        CustomerDTO customersdto=mapper.map(customer,CustomerDTO.class);
        if (customer == null) {
            return new ResponseEntity(new CustomErrorType("Customer with ssn " + ssn + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CustomerDTO>(customersdto, HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{ssn}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("ssn") long ssn, @RequestBody Customer customer) {
 
        Customer currentCustomer = customerManager.getCustomerBySSN(ssn);
 
        if (currentCustomer == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Customer with ssn " + ssn + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentCustomer.setSsn(customer.getSsn());
        currentCustomer.setFirst_name(customer.getFirst_name());
        currentCustomer.setLast_name(customer.getLast_name());
        currentCustomer.setDob(customer.getDob());
        currentCustomer.setGender(customer.getGender());
 
        customerManager.updateBySSN(currentCustomer);
        CustomerDTO customersdto=mapper.map(currentCustomer,CustomerDTO.class);
        return new ResponseEntity<CustomerDTO>(customersdto, HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{ssn}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteUser(@PathVariable("ssn") long ssn) {
 
        Customer customer = customerManager.getCustomerBySSN(ssn);
        if (customer == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with ssn " + ssn + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        customerManager.deleteBySSN(ssn);
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }
   
    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
    	System.out.println(customer.getSsn());
        if (customerManager.getCustomerBySSN(customer.getSsn()) != null) {
        	customerManager.updateBySSN(customer);
            return new ResponseEntity(new CustomErrorType(" Customer with ssn " + customer.getSsn() + " updated."),HttpStatus.CONFLICT);
        }
        customerManager.insert(customer);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/customers/getCustomer/{ssn}").buildAndExpand(customer.getSsn()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
 
    
 
    
}
