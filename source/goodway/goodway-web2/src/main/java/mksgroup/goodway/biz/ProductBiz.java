package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Product;

public interface ProductBiz {

	boolean updateProducts(Iterable<Product> products, List<Integer> tobeDeletedIds);
    
    CrudRepository<Product, Integer> getRepo();
}
