/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class CustomerController {

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

}
