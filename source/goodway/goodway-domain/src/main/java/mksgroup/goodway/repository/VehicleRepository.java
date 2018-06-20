package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    
}
