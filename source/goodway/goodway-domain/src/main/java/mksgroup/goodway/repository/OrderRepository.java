package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.OrderMaster;

public interface OrderRepository extends CrudRepository<OrderMaster, Long> {
 
}
