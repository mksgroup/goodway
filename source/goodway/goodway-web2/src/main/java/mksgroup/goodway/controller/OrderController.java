/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.OrderRepository;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderProductRepository orderProductRepository;
    
    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping({"/order","/order/search"})
    public String goOrderSearch() {
        return "order/search";
    }
    
    
    @GetMapping("/order/details")
    public String goOrderDetails() {
        return "order/details";
    }
    
    
    @GetMapping("order/load-order/address")
    public String goOrderDetailsAddressManagement() {
        return "customer/address-management";
    }
    
    /**
     * Load danh sách các đơn hàng.
     * @param packageId
     * @return
     */
    @GetMapping("/order/load-order")
    @ResponseBody
    public Iterable<OrderMaster> loadOrder() {

        Iterable<OrderMaster> orders = orderRepository.findAll();
        
        return orders;
    }
    
    /**
     * Load orderMaster's details.
     * @param orderId
     * @return
     */
    @GetMapping("/order/details/load-product")
    @ResponseBody
    public Iterable<OrderDetailProduct> loadOrderDetails(@RequestParam("orderId") Integer orderId) {

        OrderMaster order = orderRepository.findById(orderId).get();

        Iterable<OrderDetailProduct> orderProducts = orderProductRepository.findAll();
        
        return orderProducts;
    }
}
