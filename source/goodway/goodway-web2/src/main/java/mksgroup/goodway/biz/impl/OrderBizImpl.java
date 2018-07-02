package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.OrderBiz;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.OrderRepository;

@Service
public class OrderBizImpl implements OrderBiz{
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public boolean updateOrder(OrderMaster order, List<Integer> tobeDeletedIds) {
        if(tobeDeletedIds != null) {
            tobeDeletedIds.forEach(t -> {
                orderProductRepository.delete(orderProductRepository.findByOrderIdAndProductId(order, t));
            });
        }
        
        orderRepository.save(order);
        
        return true;
    }

    @Override
    public OrderMaster findByName(String name) {
        
        return orderRepository.findByName(name);
    }

    @Override
    public CrudRepository<OrderMaster, Integer> getRepo() {
        
        return orderRepository;
    }

}
