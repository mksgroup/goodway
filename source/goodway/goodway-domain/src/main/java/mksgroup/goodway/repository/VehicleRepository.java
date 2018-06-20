package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    
}
