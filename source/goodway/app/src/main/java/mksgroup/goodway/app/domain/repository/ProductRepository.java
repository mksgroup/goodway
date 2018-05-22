package mksgroup.goodway.app.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mksgroup.goodway.entity.Product;

@Repository
@Transactional
public class ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Product findById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    public void create(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(product);
    }
    
    public List<Product> findProductByPackage(Integer packageId) {
        List<Product> demoList = new ArrayList<>();

        demoList.add(new Product("Tủ lạnh S1", 0.9, 0.8, 1.0, 21));
        demoList.add(new Product("Tủ lạnh A1", 0.95, 0.99, 1.5, 25));
        
        return demoList;
    }
}
