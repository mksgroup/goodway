/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import mksgroup.goodway.biz.AddressBiz;
import mksgroup.goodway.entity.Address;
import mksgroup.goodway.repository.AddressRepository;

/**
 * @author ThachLN
 *
 */
@Service
public class AddressBizImpl implements AddressBiz {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public boolean updateAddresses(Iterable<Address> addresses, List<Integer> tobeDeletedIds) {
        
        if (tobeDeletedIds != null) {
            tobeDeletedIds.forEach(deletedId -> {
                addressRepository.deleteById(deletedId);
            });
        }
        
        addressRepository.saveAll(addresses);

        return true;
    }

    @Override
    public CrudRepository<Address, Integer> getRepo() {
        return addressRepository;
    }

}
