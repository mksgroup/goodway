/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.model.CustomerModel;
import mksgroup.goodway.repository.AddressRepository;
import mksgroup.goodway.repository.CustomerRepository;
import mksgroup.goodway.util.AppUtil;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class CustomerController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private AddressRepository addressRepository;
    
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
    
    @GetMapping("/customer/delete")
    @ResponseBody
    public Iterable<Customer> loadCustomer(@RequestParam("customerId") Integer customerId){
        
        customerRepository.deleteById(customerId);        
        Iterable<Customer> customers = customerRepository.findAll();
        
        return customers;
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
            
            // Update default Address for Customer
            Address defaultAddr = addressRepository.findById(1).get();

            entities.forEach(customer -> {
                customer.setAddressId(defaultAddr);
                customer.setAddressId1(defaultAddr);
                customer.setAddressId2(defaultAddr);
                customer.setAddressId3(defaultAddr);
                customer.setAddressId4(defaultAddr);
            });
            
            List<Customer> entityList = new ArrayList<Customer>();
            entities.forEach(e-> entityList.add(e));
            List<Customer> customers = (List<Customer>) customerRepository.findAll();
            for(Customer c : customers) {
                if(!entityList.contains(c)) {
                    customerRepository.delete(c);
                }
            }
            
            customerRepository.saveAll(entities);
            LOG.info("customerModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        Iterable<Customer> orders = customerRepository.findAll();
        
        return orders;
    }

}
