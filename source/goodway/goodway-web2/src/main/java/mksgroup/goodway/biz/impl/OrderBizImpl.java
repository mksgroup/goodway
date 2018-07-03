package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.OrderBiz;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.repository.OrderProductRepository;
import mksgroup.goodway.repository.OrderRepository;
import mksgroup.goodway.repository.ProductRepository;

@Service
public class OrderBizImpl implements OrderBiz{
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public OrderMaster findByName(String name) {
        
        return orderRepository.findByName(name);
    }

    @Override
    public CrudRepository<OrderMaster, Integer> getRepo() {
        
        return orderRepository;
    }

    @Override
    public List<Object[]> findOrderCustomer() {
        
        return orderRepository.findOrderCustomer();
    }

}
