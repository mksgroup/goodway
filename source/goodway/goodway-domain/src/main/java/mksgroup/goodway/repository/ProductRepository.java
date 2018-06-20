package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
