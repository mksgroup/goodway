package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.CustomerBiz;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.repository.CustomerRepository;

@Service
public class CustomerBizImpl implements CustomerBiz{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean updateCustomer(Iterable<Customer> customers, List<Integer> tobeDeletedIds) {
        if(tobeDeletedIds != null) {
            tobeDeletedIds.forEach(t -> {
                customerRepository.deleteById(t);
            });
        }
        
        customerRepository.saveAll(customers);
        
        return true;
    }

    @Override
    public CrudRepository<Customer, Integer> getRepo() {

        return customerRepository;
    }
    
    
}
