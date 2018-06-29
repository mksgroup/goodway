package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.ProductBiz;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.repository.ProductRepository;

@Service
public class ProductBizImpl implements ProductBiz {

	 @Autowired
	 private ProductRepository productRepository;

	 @Override
	 public boolean updateProducts(Iterable<Product> products, List<Integer> tobeDeletedIds) {
	        
	        if (tobeDeletedIds != null) {
	            tobeDeletedIds.forEach(deletedId -> {
	            	productRepository.deleteById(deletedId);
	            });
	        }
	        
	        productRepository.saveAll(products);

	        return true;
	    }

	    @Override
	    public CrudRepository<Product, Integer> getRepo() {
	        return productRepository;
	    }
}
