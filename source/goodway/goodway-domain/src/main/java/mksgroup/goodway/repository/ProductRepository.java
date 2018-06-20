package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
