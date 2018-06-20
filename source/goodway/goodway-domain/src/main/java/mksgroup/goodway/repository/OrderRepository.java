package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.OrderMaster;

@Repository
public interface OrderRepository extends CrudRepository<OrderMaster, Long> {
 
}
