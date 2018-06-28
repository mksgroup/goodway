package mksgroup.goodway.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
    
    @Query("SELECT a FROM Address a WHERE a.displayAddress = :displayAddress")
    Address findByDisplayAddress(@Param("displayAddress") String displayAddress);
}
