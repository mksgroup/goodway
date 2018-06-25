/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.model.OrderModel;
import mksgroup.goodway.repository.AddressRepository;
import mksgroup.goodway.repository.CustomerRepository;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.OrderRepository;
import mksgroup.goodway.repository.ProductRepository;
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
    private ProductRepository productRepository;
    
    @Autowired
    private OrderProductRepository orderProductRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired 
    private CustomerRepository customerRepository;
    
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
     * Load orderMaster's products.
     * @param orderId
     * @return
     */
    @GetMapping("/order/load-product")
    @ResponseBody
    public List<Product> loadOrderDetails(@RequestParam("orderCd") String orderCd) {

        Iterable<OrderMaster> orders = orderRepository.findAll();
        OrderMaster order = new OrderMaster();
        for(OrderMaster o : orders) {
            if(o.getName().equalsIgnoreCase(orderCd)) {
                order = o;
            }
        }
        List<OrderDetailProduct> orderProducts = order.getOrderDetailProductList();
        List<Product> productList = new ArrayList<Product>();

        orderProducts.forEach(p -> productList.add(productRepository.findById(p.getProductId().getId()).get()));
        
        return productList;
    }
    
    @PostMapping("/order/save")
    @ResponseBody
    @Transactional
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
            addressRepository.save(orderMaster.getAddressId());
            
            Customer customer = customerRepository.findByName(orderMaster.getCustomerId().getName());
            customer.setAddr(orderMaster.getCustomerId().getAddr());
            customerRepository.save(customer);
            
            List<OrderDetailProduct> orderDetailProduct = orderMaster.getOrderDetailProductList();
            for(OrderDetailProduct p : orderDetailProduct) {
                productRepository.save(p.getProductId());
            }
            
            orderRepository.save(orderMaster);
            LOG.info("Saved order with ID = " + orderMaster.getId());
        }
      
        return orderMaster;
    }
}
