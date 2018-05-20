package mksgroup.goodway.app.domain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mksgroup.goodway.entity.deliveryplan.DeliveryPlan;
@ExtendWith(SpringExtension.class)
public class DeliveryPlanRepositoryTest {
    @MockBean
    private SessionFactory sessionFactory;

    @MockBean
    private Session session;

    private DeliveryPlanRepository dpRepository;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
        this.dpRepository = new DeliveryPlanRepository(this.sessionFactory);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testCreate() {

        DeliveryPlan dp = new DeliveryPlan();
        
        dp.setStatus(1);
        dpRepository.create(dp);
    }

}
