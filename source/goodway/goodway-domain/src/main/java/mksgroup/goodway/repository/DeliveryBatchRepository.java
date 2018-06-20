package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.DeliveryBatch;

public interface DeliveryBatchRepository extends CrudRepository<DeliveryBatch, Long> {
    
}
