/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.repository.CustomerRepository;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping({"/customer", "/customer/search"})
    public String goCustomerSearch() {
        return "customer/search";
    }

    @RequestMapping("/customer/new")
    public String goCustomerNew() {
        return "customer/new";
    }
    
    
    /**Load danh sach customer
     * 
     * @return
     */
    @GetMapping("/customer/load-customer")
    @ResponseBody
    public Iterable<Customer> loadCustomer(){
    	Iterable<Customer> customer = customerRepository.findAll();
    	
    	return customer;
    }

}
