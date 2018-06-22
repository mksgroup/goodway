/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Address;
import mksgroup.goodway.repository.AddressRepository;

/**
 * This controller processes requests about Address.
 * @author ThachLN
 */
@Controller
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    
    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping({"/address", "/address/search"})
    public String goAddressearch() {
        return "address/search";
    }

    @RequestMapping("/address/new")
    public String goAddressrNew() {
        return "Address/new";
    }
    
    
    /**
     * Load danh sách các xe.
     * @param packageId
     * @return
     */
    @GetMapping("/address/load-address")
    @ResponseBody
    public Iterable<Address> loadAddress() {

        Iterable<Address> address = addressRepository.findAll();
        
        return address;
    }
}
