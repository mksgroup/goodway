package mksgroup.goodway.app.domain.repository;

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
}
