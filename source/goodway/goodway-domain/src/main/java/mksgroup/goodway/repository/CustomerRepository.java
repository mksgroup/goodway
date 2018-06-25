package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByName(String name);
}
