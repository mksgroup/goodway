package mksgroup.goodway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderMaster;

@Repository
public interface OrderRepository extends CrudRepository<OrderMaster, Integer> {
	
	@Query("SELECT o FROM OrderMaster o WHERE o.customerId = :customerId")
	OrderMaster findByCustomerId(@Param("customerId") Customer customerId);
}
