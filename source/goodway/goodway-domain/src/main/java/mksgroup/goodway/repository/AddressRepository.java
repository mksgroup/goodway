package mksgroup.goodway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    
}
