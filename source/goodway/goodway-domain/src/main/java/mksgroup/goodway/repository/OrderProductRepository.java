package mksgroup.goodway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.entity.OrderMaster;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderDetailProduct, Integer> {
    @Query("SELECT o FROM OrderDetailProduct o WHERE o.orderId = :orderId")
    Iterable<OrderDetailProduct> findAllByOrderId(@Param("orderId") OrderMaster orderId);
}
