package mksgroup.goodway.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import mksgroup.goodway.entity.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    VehicleRepository repo;

    @Test
    public void testSave() {
        Vehicle entity = new Vehicle();
        entity.setName("Truck1");
        entity.setCreated(new Date());
        entity.setCreatedbyUsername("test");

        entityManager.persist(entity);
//        repo.save(entity);
    }

    @Test
    void testSaveAll() {
        fail("Not yet implemented");
    }

    @Test
    void testFindById() {
        fail("Not yet implemented");
    }

    @Test
    void testExistsById() {
        fail("Not yet implemented");
    }

    @Test
    void testFindAll() {
        fail("Not yet implemented");
    }

    @Test
    void testFindAllById() {
        fail("Not yet implemented");
    }

    @Test
    void testCount() {
        fail("Not yet implemented");
    }

    @Test
    void testDeleteById() {
        fail("Not yet implemented");
    }

    @Test
    void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    void testDeleteAllIterableOfQextendsT() {
        fail("Not yet implemented");
    }

    @Test
    void testDeleteAll() {
        fail("Not yet implemented");
    }

}
