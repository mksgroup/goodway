package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.OrderMaster;

public interface OrderBiz {
        
    OrderMaster findByName(String name);
    
    List<Object[]> findOrderCustomer();
    
    CrudRepository<OrderMaster, Integer> getRepo();

}
