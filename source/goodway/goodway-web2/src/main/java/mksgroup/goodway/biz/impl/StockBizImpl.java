package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.StockBiz;
import mksgroup.goodway.entity.Stock;
import mksgroup.goodway.repository.StockRepository;

@Service
public class StockBizImpl implements StockBiz {
    
    @Autowired
    private StockRepository stockRepository;

    @Override
    public boolean updateStock(Iterable<Stock> stocks, List<Integer> tobeDeletedIds) {
        if(tobeDeletedIds != null) {
            tobeDeletedIds.forEach(t -> {
                stockRepository.deleteById(t);
            });
        }
        
        stockRepository.saveAll(stocks);
        
        return true;
    }

    @Override
    public CrudRepository<Stock, Integer> getRepo() {

        return stockRepository;
    }
    
    
}
