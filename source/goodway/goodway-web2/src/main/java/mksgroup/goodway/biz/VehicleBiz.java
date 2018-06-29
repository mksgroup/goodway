/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Vehicle;

/**
 * @author ThachLN
 *
 */
public interface VehicleBiz {
    
    /**
     * Save all vehicles and deleted all marked ids.
     * @param vehicles list of vehicles
     * @param tobeDeletedIds list of marked vehicle id to be deleted.
     * @return true of there is at least record are update or deleted.
     */
    boolean updateVehicles(Iterable<Vehicle> vehicles, List<Integer> tobeDeletedIds);
    
    CrudRepository<Vehicle, Integer> getRepo();
}
