package mksgroup.goodway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mksgroup.goodway.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    Customer findByName(@Param("name") String name);
    
    @Query("SELECT c FROM Customer c INNER JOIN OrderMaster o ON c.id = o.customerId AND o.name = :orderCd")
    Customer findByOrderCode(@Param("orderCd") String orderCd);
}
