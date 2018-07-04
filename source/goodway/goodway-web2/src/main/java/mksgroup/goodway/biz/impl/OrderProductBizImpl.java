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

    /**
     * Find OrderDetailProduct by OrderMaster and Product then delete it if exists.
     * Check if OrderMaster's OrderDetailProduct exists, if it's exists update it's quantity then add to List<OrderDetailProduct> result.
     * If it's not exists add OrderDetailProduct's orderId to do the mapping foreign key when spring boot (hibernate) execute function save(). 
     * 
     * @param order
     * @param tobeDeletedIds
     * @return
     * @see mksgroup.goodway.biz.OrderProductBiz#updateOrderProducts(mksgroup.goodway.entity.OrderMaster,
     *      java.util.List)
     */
    @Override
    public List<OrderDetailProduct> updateOrderProducts(OrderMaster order, List<Integer> tobeDeletedIds) {

        if (tobeDeletedIds != null) {
            tobeDeletedIds.forEach(t -> {
                Product product = productRepository.findById(t).get();
                OrderDetailProduct existedOrderProduct = orderProductRepository.findByOrderIdAndProductId(order,
                        product);
                if (existedOrderProduct != null) {
                    orderProductRepository.delete(existedOrderProduct);
                }
            });
        }

        List<OrderDetailProduct> result = new ArrayList<OrderDetailProduct>();

        for (OrderDetailProduct o : order.getOrderDetailProductList()) {
            if (o != null) {
                Product product = productRepository.findById(o.getProductId().getId()).get();
                o.setOrderId(order);

                if (order.getId() != null) {
                    OrderDetailProduct orderProduct = orderProductRepository.findByOrderIdAndProductId(order, product);

                    if (orderProduct != null) {
                        orderProduct.setQuantity(o.getQuantity());

                        result.add(orderProduct);
                    } else {
                        
                        result.add(o);
                    }
                    
                } else {

                    result.add(o);
                }
            }
        }
        return result;
    }

    /**
     * Find OrderDetailProduct by OrderMaster and Product.
     * @param orderId
     * @param productId
     * @return
     * @see mksgroup.goodway.biz.OrderProductBiz#findByOrderIdAndProductId(mksgroup.goodway.entity.OrderMaster, mksgroup.goodway.entity.Product)
     */
    @Override
    public OrderDetailProduct findByOrderIdAndProductId(OrderMaster orderId, Product productId) {

        return orderProductRepository.findByOrderIdAndProductId(orderId, productId);
    }

    /**
     * return OrderDetailProduct repository
     * @return OrderProductRepository
     * @see mksgroup.goodway.biz.OrderProductBiz#getRepo()
     */
    @Override
    public CrudRepository<OrderDetailProduct, Integer> getRepo() {

        return orderProductRepository;
    }

}
