package mksgroup.goodway.app.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mksgroup.goodway.entity.Vehicle;

@Repository
@Transactional
public class VehicleRepository {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Vehicle findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Vehicle.class, id);
    }

    public void create(Vehicle vehicle) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(vehicle);
    }

    public List<Vehicle> findVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        
        vehicles.add(new Vehicle("Tải nhẹ A1", 2.5, 2, 2.5, 3));
        vehicles.add(new Vehicle("Tải nặng C1", 7, 2, 2.5, 3));
        
        return vehicles;
    }
}
