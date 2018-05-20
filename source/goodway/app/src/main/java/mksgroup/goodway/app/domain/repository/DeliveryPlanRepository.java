package mksgroup.goodway.app.domain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mksgroup.goodway.entity.deliveryplan.DeliveryPlan;

@Repository
@Transactional
public class DeliveryPlanRepository {
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public DeliveryPlanRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DeliveryPlan findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(DeliveryPlan.class, id);
    }

    public void create(DeliveryPlan dp) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(dp);
    }
}
