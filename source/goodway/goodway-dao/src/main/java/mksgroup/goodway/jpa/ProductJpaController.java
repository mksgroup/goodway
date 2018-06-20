/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mksgroup.goodway.entity.OrderDetailProduct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.jpa.exceptions.IllegalOrphanException;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getOrderDetailProductCollection() == null) {
            product.setOrderDetailProductCollection(new ArrayList<OrderDetailProduct>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OrderDetailProduct> attachedOrderDetailProductCollection = new ArrayList<OrderDetailProduct>();
            for (OrderDetailProduct orderDetailProductCollectionOrderDetailProductToAttach : product.getOrderDetailProductCollection()) {
                orderDetailProductCollectionOrderDetailProductToAttach = em.getReference(orderDetailProductCollectionOrderDetailProductToAttach.getClass(), orderDetailProductCollectionOrderDetailProductToAttach.getId());
                attachedOrderDetailProductCollection.add(orderDetailProductCollectionOrderDetailProductToAttach);
            }
            product.setOrderDetailProductCollection(attachedOrderDetailProductCollection);
            em.persist(product);
            for (OrderDetailProduct orderDetailProductCollectionOrderDetailProduct : product.getOrderDetailProductCollection()) {
                Product oldProductIdOfOrderDetailProductCollectionOrderDetailProduct = orderDetailProductCollectionOrderDetailProduct.getProductId();
                orderDetailProductCollectionOrderDetailProduct.setProductId(product);
                orderDetailProductCollectionOrderDetailProduct = em.merge(orderDetailProductCollectionOrderDetailProduct);
                if (oldProductIdOfOrderDetailProductCollectionOrderDetailProduct != null) {
                    oldProductIdOfOrderDetailProductCollectionOrderDetailProduct.getOrderDetailProductCollection().remove(orderDetailProductCollectionOrderDetailProduct);
                    oldProductIdOfOrderDetailProductCollectionOrderDetailProduct = em.merge(oldProductIdOfOrderDetailProductCollectionOrderDetailProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Collection<OrderDetailProduct> orderDetailProductCollectionOld = persistentProduct.getOrderDetailProductCollection();
            Collection<OrderDetailProduct> orderDetailProductCollectionNew = product.getOrderDetailProductCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderDetailProduct orderDetailProductCollectionOldOrderDetailProduct : orderDetailProductCollectionOld) {
                if (!orderDetailProductCollectionNew.contains(orderDetailProductCollectionOldOrderDetailProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetailProduct " + orderDetailProductCollectionOldOrderDetailProduct + " since its productId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OrderDetailProduct> attachedOrderDetailProductCollectionNew = new ArrayList<OrderDetailProduct>();
            for (OrderDetailProduct orderDetailProductCollectionNewOrderDetailProductToAttach : orderDetailProductCollectionNew) {
                orderDetailProductCollectionNewOrderDetailProductToAttach = em.getReference(orderDetailProductCollectionNewOrderDetailProductToAttach.getClass(), orderDetailProductCollectionNewOrderDetailProductToAttach.getId());
                attachedOrderDetailProductCollectionNew.add(orderDetailProductCollectionNewOrderDetailProductToAttach);
            }
            orderDetailProductCollectionNew = attachedOrderDetailProductCollectionNew;
            product.setOrderDetailProductCollection(orderDetailProductCollectionNew);
            product = em.merge(product);
            for (OrderDetailProduct orderDetailProductCollectionNewOrderDetailProduct : orderDetailProductCollectionNew) {
                if (!orderDetailProductCollectionOld.contains(orderDetailProductCollectionNewOrderDetailProduct)) {
                    Product oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct = orderDetailProductCollectionNewOrderDetailProduct.getProductId();
                    orderDetailProductCollectionNewOrderDetailProduct.setProductId(product);
                    orderDetailProductCollectionNewOrderDetailProduct = em.merge(orderDetailProductCollectionNewOrderDetailProduct);
                    if (oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct != null && !oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct.equals(product)) {
                        oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct.getOrderDetailProductCollection().remove(orderDetailProductCollectionNewOrderDetailProduct);
                        oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct = em.merge(oldProductIdOfOrderDetailProductCollectionNewOrderDetailProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderDetailProduct> orderDetailProductCollectionOrphanCheck = product.getOrderDetailProductCollection();
            for (OrderDetailProduct orderDetailProductCollectionOrphanCheckOrderDetailProduct : orderDetailProductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the OrderDetailProduct " + orderDetailProductCollectionOrphanCheckOrderDetailProduct + " in its orderDetailProductCollection field has a non-nullable productId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
