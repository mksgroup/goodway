package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Customer;

public interface CustomerBiz {

    boolean updateCustomer(Iterable<Customer> customers, List<Integer> tobeDeletedIds);
    
    CrudRepository<Customer, Integer> getRepo();
}
