package mksgroup.goodway.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import mksgroup.goodway.entity.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class VehicleRepositoryTest {
    
    @Autowired
    VehicleRepository repo;

    @Test
    public void testSave() {
        Vehicle entity = new Vehicle();
        entity.setName("Truck1");
        entity.setCreated(new Date());
        entity.setCreatedbyUsername("thachln");

        repo.save(entity);
        
        Iterable<Vehicle> vehicles = repo.findAll();
        assertNotNull(vehicles);
        Vehicle vehicle = vehicles.iterator().next();
        assertEquals("Truck1", vehicle.getName());
        assertEquals("thachln", vehicle.getCreatedbyUsername());
    }

    @Test
    void testSaveAll() {

    }

    @Test
    void testFindById() {

    }

    @Test
    void testExistsById() {
        
    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindAllById() {
        
    }

    @Test
    void testCount() {
        
    }

    @Test
    void testDeleteById() {
        
    }

    @Test
    void testDelete() {
        
    }

    @Test
    void testDeleteAllIterableOfQextendsT() {
        
    }

    @Test
    void testDeleteAll() {
        
    }

}
