/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import mksgroup.goodway.biz.OrderBiz;
import mksgroup.goodway.biz.OrderProductBiz;
import mksgroup.goodway.biz.ProductBiz;
import mksgroup.goodway.entity.Address;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.model.OrderDetailProductModel;
import mksgroup.goodway.model.OrderModel;
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
    private OrderBiz orderBiz;

    @Autowired
    private ProductBiz productBiz;

    @Autowired
    private OrderProductBiz orderProductBiz;

    @Autowired
    private CustomerBiz customerBiz;

    @Autowired
    private AddressBiz addressBiz;

    /**
     * Goto the OrderMaster index page.
     * @return
     */
    @RequestMapping({"/order", "/order/search"})
    public String goOrderSearch() {
        return "order/search";
    }
    
    /**
     * 
     * Goto add new OrderMaster page.
     * @param model
     * @return
     */
    @GetMapping("/order/new")
    public String goOrderDetails(Model model) {
        model.addAttribute("map_key", mapKey);

        return "order/new";
    }

    /**
     * Go to edit OrderMaster page.
     * @param orderCd
     * @param model
     * @return
     */
    @GetMapping("order/edit/{orderCd}")
    public String goOrderEdit(@PathVariable("orderCd") String orderCd, Model model) {
        model.addAttribute("orderCode", orderCd);
        model.addAttribute("map_key", mapKey);

        return "order/new";
    }
    
    /**
     * 
     * Delete OrderMaster and its OrderDetailProduct by orderId.
     * @param orderId
     * @return
     */
    @GetMapping("order/delete")
    @ResponseBody
    public List<Object[]> deleteOrder(@RequestParam("orderCd") String orderCd) {
        OrderMaster order = orderBiz.findByName(orderCd);
        orderBiz.getRepo().delete(order);
        List<Object[]> orders = orderBiz.findOrderCustomer();
        
        return orders;
    }

    /**
     * Load OrderMaster's List<OrderDetailProduct>.
     * @param orderCd
     * @return
     */
    @GetMapping("/order/load-orderProduct")
    @ResponseBody
    public List<OrderDetailProductModel> getOrderProduct(@RequestParam("orderCd") String orderCd) {
        OrderMaster orderMaster = orderBiz.findByName(orderCd);
        LOG.info(orderMaster.toString());

        List<OrderDetailProduct> orderProductList = orderMaster.getOrderDetailProductList();

        List<OrderDetailProductModel> products = new ArrayList<>();

        for (OrderDetailProduct o : orderProductList) {
            OrderDetailProductModel model = new OrderDetailProductModel();

            model.setId(o.getProductId().getId());
            model.setName(o.getProductId().getName());
            model.setHeight(o.getProductId().getHeight());
            model.setWidth(o.getProductId().getWidth());
            model.setLength(o.getProductId().getLength());
            model.setWeight(o.getProductId().getWeight());
            model.setQuantity(o.getQuantity());

            products.add(model);
        }

        return products;
    }

    /**
     * Load List<OrderMaster>.
     * @param packageId
     * @return
     */
    @GetMapping("/order/load-order")
    @ResponseBody
    public Iterable<OrderMaster> loadOrder() {

        Iterable<OrderMaster> orders = orderBiz.getRepo().findAll();

        return orders;
    }
    
    /**
     * 
     * Save OrderMaster.
     * @param data
     * @param errors
     * @param request
     * @return
     */
    @PostMapping("/order/save")
    @ResponseBody
    @Transactional
    public OrderMaster saveOrder(@Valid @RequestBody OrderModel data, Errors errors, HttpServletRequest request) {
        OrderMaster orderMaster = null;
        LOG.info("saveOrder....");

        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

            return null;
        } else {

            LOG.info("getting order's data");
            orderMaster = AppUtil.parseOrder(data);

//            Customer customer = customerBiz.getRepo().findById(orderMaster.getCustomerId().getId()).get();
//            LOG.info(customer.toString());

//            orderMaster.setCustomerId(customer);

            Address addr = addressBiz.findByDisplayAddress(orderMaster.getAddressId().getDisplayAddress());
            LOG.info(addr.toString());

            orderMaster.setAddressId(addr);

            // If order existed set orderMaster's id = existed oder's id
            OrderMaster existsOrder = orderBiz.findByName(data.getOrderCd());
            if (existsOrder != null) {
                orderMaster.setId(existsOrder.getId());
            }
            
            LOG.info("Setting orderProduct's data");
            orderMaster.setOrderDetailProductList(orderProductBiz.updateOrderProducts(orderMaster, data.getDeletedIds()));
            orderMaster.setCreatedbyUsername("Nam Tang");
            orderMaster.setDeliveryDate(new Date());

            orderBiz.getRepo().save(orderMaster);
            LOG.info("Saved order with ID = " + orderMaster.getId());
        }

        return orderMaster;
    }
}
