/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mksgroup.goodway.entity.Address;

/**
 * @author ThachLN
 *
 */
public interface AddressBiz {
    
    /**
     * Save all vehicles and deleted all marked ids.
     * @param vehicles list of vehicles
     * @param tobeDeletedIds list of marked vehicle id to be deleted.
     * @return true of there is at least record are update or deleted.
     */
    boolean updateAddresses(Iterable<Address> vehicles, List<Integer> tobeDeletedIds);
    
    Address findByDisplayAddress(String displayAddress);
    
    CrudRepository<Address, Integer> getRepo();
}
