/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.AddressBiz;
import mksgroup.goodway.biz.CustomerBiz;
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
    private AddressBiz addressBiz;
    
	@Autowired
	private CustomerBiz customerBiz;
	
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
    	Iterable<Customer> customer = customerBiz.getRepo().findAll();
    	
    	return customer;
    }
    
    /**
     * 
     * Load customer by order's name.
     * @return
     */
    @GetMapping("/customer/load-customerBy")
    @ResponseBody
    public Customer loadCustomerByOrderName(@RequestParam("orderCd") String orderCd){
        
        return ((CustomerRepository) customerBiz.getRepo()).findByOrderCode(orderCd);
    }
    
    
    @GetMapping("/customer/load-customer/{id}")
    @ResponseBody
    public Customer loadCustomerById(@PathVariable("id") Integer customerId){
        Customer customer = customerBiz.getRepo().findById(customerId).get();
        
        return customer;
    }
    
    @GetMapping("/customer/delete")
    @ResponseBody
    public Iterable<Customer> loadCustomer(@RequestParam("customerId") Integer customerId){
        
        customerBiz.getRepo().deleteById(customerId);        
        Iterable<Customer> customers = customerBiz.getRepo().findAll();
        
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
            Address defaultAddr = addressBiz.getRepo().findAll().iterator().next();
            
            List<Customer> entityList = new ArrayList<Customer>();

            entities.forEach(customer -> {
                if (customer.getName() != null && !customer.getName().isEmpty()) {
                    if (customer.getAddr() == null || customer.getAddr().isEmpty()) {
                        customer.setAddressId(defaultAddr);
                        customer.setAddressId1(defaultAddr);
                        customer.setAddressId2(defaultAddr);
                        customer.setAddressId3(defaultAddr);
                        customer.setAddressId4(defaultAddr);
                    } else {
                        // Load address from db
                        Address addr = ((AddressRepository) addressBiz.getRepo()).findByDisplayAddress(customer.getAddr());
                        
                        if (addr == null ) { addr = defaultAddr; }
                        customer.setAddressId(addr);
                        customer.setAddressId1(addr);
                        customer.setAddressId2(addr);
                        customer.setAddressId3(addr);
                        customer.setAddressId4(addr);
                    }
                    entityList.add(customer);
                }
            });
            
            customerBiz.updateCustomer(entities, data.getDeletedIds());
            LOG.info("customerModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        return customerBiz.getRepo().findAll();
    }

}
