package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Stock;

public interface StockBiz {

    boolean updateStock(Iterable<Stock> stocks, List<Integer> tobeDeletedIds);
    
    CrudRepository<Stock, Integer> getRepo();
}
