/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.entity.OrderDetail;
import mksgroup.goodway.entity.OrderDetailProduct;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class OrderDetailProductJpaController implements Serializable {

    public OrderDetailProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderDetailProduct orderDetailProduct) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product productId = orderDetailProduct.getProductId();
            if (productId != null) {
                productId = em.getReference(productId.getClass(), productId.getId());
                orderDetailProduct.setProductId(productId);
            }
            OrderDetail orderDetailId = orderDetailProduct.getOrderDetailId();
            if (orderDetailId != null) {
                orderDetailId = em.getReference(orderDetailId.getClass(), orderDetailId.getId());
                orderDetailProduct.setOrderDetailId(orderDetailId);
            }
            em.persist(orderDetailProduct);
            if (productId != null) {
                productId.getOrderDetailProductCollection().add(orderDetailProduct);
                productId = em.merge(productId);
            }
            if (orderDetailId != null) {
                orderDetailId.getOrderDetailProductCollection().add(orderDetailProduct);
                orderDetailId = em.merge(orderDetailId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderDetailProduct orderDetailProduct) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderDetailProduct persistentOrderDetailProduct = em.find(OrderDetailProduct.class, orderDetailProduct.getId());
            Product productIdOld = persistentOrderDetailProduct.getProductId();
            Product productIdNew = orderDetailProduct.getProductId();
            OrderDetail orderDetailIdOld = persistentOrderDetailProduct.getOrderDetailId();
            OrderDetail orderDetailIdNew = orderDetailProduct.getOrderDetailId();
            if (productIdNew != null) {
                productIdNew = em.getReference(productIdNew.getClass(), productIdNew.getId());
                orderDetailProduct.setProductId(productIdNew);
            }
            if (orderDetailIdNew != null) {
                orderDetailIdNew = em.getReference(orderDetailIdNew.getClass(), orderDetailIdNew.getId());
                orderDetailProduct.setOrderDetailId(orderDetailIdNew);
            }
            orderDetailProduct = em.merge(orderDetailProduct);
            if (productIdOld != null && !productIdOld.equals(productIdNew)) {
                productIdOld.getOrderDetailProductCollection().remove(orderDetailProduct);
                productIdOld = em.merge(productIdOld);
            }
            if (productIdNew != null && !productIdNew.equals(productIdOld)) {
                productIdNew.getOrderDetailProductCollection().add(orderDetailProduct);
                productIdNew = em.merge(productIdNew);
            }
            if (orderDetailIdOld != null && !orderDetailIdOld.equals(orderDetailIdNew)) {
                orderDetailIdOld.getOrderDetailProductCollection().remove(orderDetailProduct);
                orderDetailIdOld = em.merge(orderDetailIdOld);
            }
            if (orderDetailIdNew != null && !orderDetailIdNew.equals(orderDetailIdOld)) {
                orderDetailIdNew.getOrderDetailProductCollection().add(orderDetailProduct);
                orderDetailIdNew = em.merge(orderDetailIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderDetailProduct.getId();
                if (findOrderDetailProduct(id) == null) {
                    throw new NonexistentEntityException("The orderDetailProduct with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderDetailProduct orderDetailProduct;
            try {
                orderDetailProduct = em.getReference(OrderDetailProduct.class, id);
                orderDetailProduct.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetailProduct with id " + id + " no longer exists.", enfe);
            }
            Product productId = orderDetailProduct.getProductId();
            if (productId != null) {
                productId.getOrderDetailProductCollection().remove(orderDetailProduct);
                productId = em.merge(productId);
            }
            OrderDetail orderDetailId = orderDetailProduct.getOrderDetailId();
            if (orderDetailId != null) {
                orderDetailId.getOrderDetailProductCollection().remove(orderDetailProduct);
                orderDetailId = em.merge(orderDetailId);
            }
            em.remove(orderDetailProduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderDetailProduct> findOrderDetailProductEntities() {
        return findOrderDetailProductEntities(true, -1, -1);
    }

    public List<OrderDetailProduct> findOrderDetailProductEntities(int maxResults, int firstResult) {
        return findOrderDetailProductEntities(false, maxResults, firstResult);
    }

    private List<OrderDetailProduct> findOrderDetailProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetailProduct.class));
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

    public OrderDetailProduct findOrderDetailProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetailProduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderDetailProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetailProduct> rt = cq.from(OrderDetailProduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
