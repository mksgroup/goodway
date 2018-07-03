package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.OrderMaster;

public interface OrderBiz {
        
    OrderMaster findByName(String name);
    
    CrudRepository<OrderMaster, Integer> getRepo();

}
