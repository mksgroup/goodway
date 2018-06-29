/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.VehicleBiz;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.repository.VehicleRepository;

/**
 * @author ThachLN
 *
 */
@Service
public class VehicleBizImpl implements VehicleBiz {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public boolean updateVehicles(Iterable<Vehicle> vehicles, List<Integer> tobeDeletedIds) {
        
        if (tobeDeletedIds != null) {
            tobeDeletedIds.forEach(deletedId -> {
                vehicleRepository.deleteById(deletedId);
            });
        }
        
        vehicleRepository.saveAll(vehicles);

        return true;
    }

    @Override
    public CrudRepository<Vehicle, Integer> getRepo() {
        return vehicleRepository;
    }

}
