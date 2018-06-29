package mksgroup.goodway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.OrderMaster;

@Repository
public interface OrderRepository extends CrudRepository<OrderMaster, Integer> {
 
    @Query("SELECT o FROM OrderMaster o WHERE o.name = :orderCd")
    OrderMaster findByName(@Param("orderCd") String orderCd);
    
	@Query(nativeQuery = true, value="select o.name as orderName, c.name as customerName, c.phone as customerPhone, c.addr as customerAddr, o.delivery_date as deliveryDate FROM goodway_customer c INNER JOIN goodway_order_master o WHERE c.id =o.customer_id")
	List<Object[]> findOrderCustomer();
	
	
}
