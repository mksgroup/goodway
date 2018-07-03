package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;

public interface OrderProductBiz {
    
    List<OrderDetailProduct> updateOrderProducts(OrderMaster order, List<Integer> tobeDeletedIds);

    OrderDetailProduct findByOrderIdAndProductId(OrderMaster orderId, Product productId);

    CrudRepository<OrderDetailProduct, Integer> getRepo();
}
