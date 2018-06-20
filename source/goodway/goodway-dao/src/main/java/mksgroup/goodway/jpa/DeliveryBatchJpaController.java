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
import mksgroup.goodway.entity.DeliveryBatch;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class DeliveryBatchJpaController implements Serializable {

    public DeliveryBatchJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DeliveryBatch deliveryBatch) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster orderId = deliveryBatch.getOrderId();
            if (orderId != null) {
                orderId = em.getReference(orderId.getClass(), orderId.getId());
                deliveryBatch.setOrderId(orderId);
            }
            em.persist(deliveryBatch);
            if (orderId != null) {
                orderId.getDeliveryBatchCollection().add(deliveryBatch);
                orderId = em.merge(orderId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DeliveryBatch deliveryBatch) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DeliveryBatch persistentDeliveryBatch = em.find(DeliveryBatch.class, deliveryBatch.getId());
            OrderMaster orderIdOld = persistentDeliveryBatch.getOrderId();
            OrderMaster orderIdNew = deliveryBatch.getOrderId();
            if (orderIdNew != null) {
                orderIdNew = em.getReference(orderIdNew.getClass(), orderIdNew.getId());
                deliveryBatch.setOrderId(orderIdNew);
            }
            deliveryBatch = em.merge(deliveryBatch);
            if (orderIdOld != null && !orderIdOld.equals(orderIdNew)) {
                orderIdOld.getDeliveryBatchCollection().remove(deliveryBatch);
                orderIdOld = em.merge(orderIdOld);
            }
            if (orderIdNew != null && !orderIdNew.equals(orderIdOld)) {
                orderIdNew.getDeliveryBatchCollection().add(deliveryBatch);
                orderIdNew = em.merge(orderIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = deliveryBatch.getId();
                if (findDeliveryBatch(id) == null) {
                    throw new NonexistentEntityException("The deliveryBatch with id " + id + " no longer exists.");
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
            DeliveryBatch deliveryBatch;
            try {
                deliveryBatch = em.getReference(DeliveryBatch.class, id);
                deliveryBatch.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deliveryBatch with id " + id + " no longer exists.", enfe);
            }
            OrderMaster orderId = deliveryBatch.getOrderId();
            if (orderId != null) {
                orderId.getDeliveryBatchCollection().remove(deliveryBatch);
                orderId = em.merge(orderId);
            }
            em.remove(deliveryBatch);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DeliveryBatch> findDeliveryBatchEntities() {
        return findDeliveryBatchEntities(true, -1, -1);
    }

    public List<DeliveryBatch> findDeliveryBatchEntities(int maxResults, int firstResult) {
        return findDeliveryBatchEntities(false, maxResults, firstResult);
    }

    private List<DeliveryBatch> findDeliveryBatchEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DeliveryBatch.class));
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

    public DeliveryBatch findDeliveryBatch(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DeliveryBatch.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeliveryBatchCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DeliveryBatch> rt = cq.from(DeliveryBatch.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
