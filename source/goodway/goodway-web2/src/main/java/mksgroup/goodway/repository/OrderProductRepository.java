package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.OrderDetailProduct;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderDetailProduct, Integer> {
 
}
