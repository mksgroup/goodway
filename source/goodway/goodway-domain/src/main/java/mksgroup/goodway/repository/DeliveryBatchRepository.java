package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.DeliveryBatch;

@Repository
public interface DeliveryBatchRepository extends CrudRepository<DeliveryBatch, Integer> {
    
}
