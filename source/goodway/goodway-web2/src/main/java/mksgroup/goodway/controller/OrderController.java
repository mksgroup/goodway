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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.model.OrderModel;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.OrderRepository;
import mksgroup.goodway.util.AppUtil;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class OrderController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(OrderController.class);
    
    @Value("${map.key}")
    String mapKey;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderProductRepository orderProductRepository;
    
    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping({"/order", "/order/search"})
    public String goOrderSearch() {
        return "order/search";
    }
    
    
    @GetMapping("/order/new")
    public String goOrderDetails(Model model) {
        model.addAttribute("map_key", mapKey);

        return "order/new";
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
    
    @PostMapping("/order/save")
    @ResponseBody
    public OrderMaster saveOrder(@Valid @RequestBody OrderModel data, Errors errors, HttpServletRequest request) {
        OrderMaster orderMaster = null;
        LOG.info("saveOrder....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return null;
        } else {
            orderMaster = AppUtil.parseOrder(data);
            
            
            orderRepository.save(orderMaster);
            LOG.info("Saved order with ID = " + orderMaster.getId());
        }
        
        
        return orderMaster;
    }
}
