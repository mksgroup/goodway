package mksgroup.goodway.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.OrderProductBiz;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.ProductRepository;

@Service
public class OrderProductBizImpl implements OrderProductBiz {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetailProduct> updateOrderProducts(OrderMaster order, List<Integer> tobeDeletedIds) {

        if (tobeDeletedIds != null) {
            tobeDeletedIds.forEach(t -> {
                Product product = productRepository.findById(t).get();
                orderProductRepository.delete(orderProductRepository.findByOrderIdAndProductId(order, product));
            });
        }

        List<OrderDetailProduct> result = new ArrayList<OrderDetailProduct>();
        OrderDetailProduct nullOrderProduct = null;

        for (OrderDetailProduct o : order.getOrderDetailProductList()) {
            // if (o != null) {
            // Product product = productRepository.findById(o.getProductId().getId()).get();
            // OrderDetailProduct orderProduct = orderProductRepository.findByOrderIdAndProductId(order, product);
            // if (!orderProduct.equals(nullOrderProduct)) {
            // orderProduct.setQuantity(o.getQuantity());
            //
            // result.add(orderProductRepository.save(orderProduct));
            // } else {
            // o.setOrderId(order);
            // o.setProductName(product.getName());
            // o.setProductId(product);
            //
            // result.add(orderProductRepository.save(o));
            // }
            // }
            if (o != null) {
                Product product = productRepository.findById(o.getProductId().getId()).get();

                if (order.getId() != null) {
                    OrderDetailProduct orderProduct = orderProductRepository.findByOrderIdAndProductId(order, product);

                    if (orderProduct != null) {
                        orderProduct.setQuantity(o.getQuantity());

                        result.add(orderProduct);
                    }
                    
                } else {

                    o.setOrderId(order);
                    o.setProductName(product.getName());
                    o.setProductId(product);

                    result.add(o);
                }
            }
        }

        return result;
    }

    @Override
    public OrderDetailProduct findByOrderIdAndProductId(OrderMaster orderId, Product productId) {

        return orderProductRepository.findByOrderIdAndProductId(orderId, productId);
    }

    @Override
    public CrudRepository<OrderDetailProduct, Integer> getRepo() {

        return orderProductRepository;
    }

}
