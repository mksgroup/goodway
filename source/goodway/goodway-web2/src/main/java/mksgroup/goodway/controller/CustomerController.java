/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.CustomerModel;
import mksgroup.goodway.repository.CustomerRepository;
import mksgroup.goodway.util.AppUtil;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class CustomerController {

	 private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	 
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
    @PostMapping("/customer/save")
    @ResponseBody
    public Iterable<Customer> saveCustomers(@Valid @RequestBody CustomerModel data, Errors errors, HttpServletRequest request) {
        LOG.info("saveCustomers....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return null;
        } else {
            Iterable<Customer> entities = AppUtil.parseCustomer(data);
            customerRepository.saveAll(entities);
            LOG.info("customerModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        Iterable<Customer> orders = customerRepository.findAll();
        
        return orders;
    }

}
