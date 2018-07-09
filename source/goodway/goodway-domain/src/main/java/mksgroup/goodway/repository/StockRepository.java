package mksgroup.goodway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    
    @Query("SELECT s FROM Stock s WHERE s.name = :name")
    Customer findByCode(@Param("name") String name);
}
