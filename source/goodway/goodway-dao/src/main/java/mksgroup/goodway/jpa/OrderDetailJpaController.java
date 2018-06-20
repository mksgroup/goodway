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
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.entity.OrderDetailProduct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mksgroup.goodway.entity.OrderDetail;
import mksgroup.goodway.jpa.exceptions.IllegalOrphanException;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class OrderDetailJpaController implements Serializable {

    public OrderDetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderDetail orderDetail) {
        if (orderDetail.getOrderDetailProductCollection() == null) {
            orderDetail.setOrderDetailProductCollection(new ArrayList<OrderDetailProduct>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster orderMasterId = orderDetail.getOrderMasterId();
            if (orderMasterId != null) {
                orderMasterId = em.getReference(orderMasterId.getClass(), orderMasterId.getId());
                orderDetail.setOrderMasterId(orderMasterId);
            }
            Collection<OrderDetailProduct> attachedOrderDetailProductCollection = new ArrayList<OrderDetailProduct>();
            for (OrderDetailProduct orderDetailProductCollectionOrderDetailProductToAttach : orderDetail.getOrderDetailProductCollection()) {
                orderDetailProductCollectionOrderDetailProductToAttach = em.getReference(orderDetailProductCollectionOrderDetailProductToAttach.getClass(), orderDetailProductCollectionOrderDetailProductToAttach.getId());
                attachedOrderDetailProductCollection.add(orderDetailProductCollectionOrderDetailProductToAttach);
            }
            orderDetail.setOrderDetailProductCollection(attachedOrderDetailProductCollection);
            em.persist(orderDetail);
            if (orderMasterId != null) {
                orderMasterId.getOrderDetailCollection().add(orderDetail);
                orderMasterId = em.merge(orderMasterId);
            }
            for (OrderDetailProduct orderDetailProductCollectionOrderDetailProduct : orderDetail.getOrderDetailProductCollection()) {
                OrderDetail oldOrderDetailIdOfOrderDetailProductCollectionOrderDetailProduct = orderDetailProductCollectionOrderDetailProduct.getOrderDetailId();
                orderDetailProductCollectionOrderDetailProduct.setOrderDetailId(orderDetail);
                orderDetailProductCollectionOrderDetailProduct = em.merge(orderDetailProductCollectionOrderDetailProduct);
                if (oldOrderDetailIdOfOrderDetailProductCollectionOrderDetailProduct != null) {
                    oldOrderDetailIdOfOrderDetailProductCollectionOrderDetailProduct.getOrderDetailProductCollection().remove(orderDetailProductCollectionOrderDetailProduct);
                    oldOrderDetailIdOfOrderDetailProductCollectionOrderDetailProduct = em.merge(oldOrderDetailIdOfOrderDetailProductCollectionOrderDetailProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderDetail orderDetail) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderDetail persistentOrderDetail = em.find(OrderDetail.class, orderDetail.getId());
            OrderMaster orderMasterIdOld = persistentOrderDetail.getOrderMasterId();
            OrderMaster orderMasterIdNew = orderDetail.getOrderMasterId();
            Collection<OrderDetailProduct> orderDetailProductCollectionOld = persistentOrderDetail.getOrderDetailProductCollection();
            Collection<OrderDetailProduct> orderDetailProductCollectionNew = orderDetail.getOrderDetailProductCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderDetailProduct orderDetailProductCollectionOldOrderDetailProduct : orderDetailProductCollectionOld) {
                if (!orderDetailProductCollectionNew.contains(orderDetailProductCollectionOldOrderDetailProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetailProduct " + orderDetailProductCollectionOldOrderDetailProduct + " since its orderDetailId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (orderMasterIdNew != null) {
                orderMasterIdNew = em.getReference(orderMasterIdNew.getClass(), orderMasterIdNew.getId());
                orderDetail.setOrderMasterId(orderMasterIdNew);
            }
            Collection<OrderDetailProduct> attachedOrderDetailProductCollectionNew = new ArrayList<OrderDetailProduct>();
            for (OrderDetailProduct orderDetailProductCollectionNewOrderDetailProductToAttach : orderDetailProductCollectionNew) {
                orderDetailProductCollectionNewOrderDetailProductToAttach = em.getReference(orderDetailProductCollectionNewOrderDetailProductToAttach.getClass(), orderDetailProductCollectionNewOrderDetailProductToAttach.getId());
                attachedOrderDetailProductCollectionNew.add(orderDetailProductCollectionNewOrderDetailProductToAttach);
            }
            orderDetailProductCollectionNew = attachedOrderDetailProductCollectionNew;
            orderDetail.setOrderDetailProductCollection(orderDetailProductCollectionNew);
            orderDetail = em.merge(orderDetail);
            if (orderMasterIdOld != null && !orderMasterIdOld.equals(orderMasterIdNew)) {
                orderMasterIdOld.getOrderDetailCollection().remove(orderDetail);
                orderMasterIdOld = em.merge(orderMasterIdOld);
            }
            if (orderMasterIdNew != null && !orderMasterIdNew.equals(orderMasterIdOld)) {
                orderMasterIdNew.getOrderDetailCollection().add(orderDetail);
                orderMasterIdNew = em.merge(orderMasterIdNew);
            }
            for (OrderDetailProduct orderDetailProductCollectionNewOrderDetailProduct : orderDetailProductCollectionNew) {
                if (!orderDetailProductCollectionOld.contains(orderDetailProductCollectionNewOrderDetailProduct)) {
                    OrderDetail oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct = orderDetailProductCollectionNewOrderDetailProduct.getOrderDetailId();
                    orderDetailProductCollectionNewOrderDetailProduct.setOrderDetailId(orderDetail);
                    orderDetailProductCollectionNewOrderDetailProduct = em.merge(orderDetailProductCollectionNewOrderDetailProduct);
                    if (oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct != null && !oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct.equals(orderDetail)) {
                        oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct.getOrderDetailProductCollection().remove(orderDetailProductCollectionNewOrderDetailProduct);
                        oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct = em.merge(oldOrderDetailIdOfOrderDetailProductCollectionNewOrderDetailProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderDetail.getId();
                if (findOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.");
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
            OrderDetail orderDetail;
            try {
                orderDetail = em.getReference(OrderDetail.class, id);
                orderDetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderDetailProduct> orderDetailProductCollectionOrphanCheck = orderDetail.getOrderDetailProductCollection();
            for (OrderDetailProduct orderDetailProductCollectionOrphanCheckOrderDetailProduct : orderDetailProductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrderDetail (" + orderDetail + ") cannot be destroyed since the OrderDetailProduct " + orderDetailProductCollectionOrphanCheckOrderDetailProduct + " in its orderDetailProductCollection field has a non-nullable orderDetailId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OrderMaster orderMasterId = orderDetail.getOrderMasterId();
            if (orderMasterId != null) {
                orderMasterId.getOrderDetailCollection().remove(orderDetail);
                orderMasterId = em.merge(orderMasterId);
            }
            em.remove(orderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderDetail> findOrderDetailEntities() {
        return findOrderDetailEntities(true, -1, -1);
    }

    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return findOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<OrderDetail> findOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetail.class));
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

    public OrderDetail findOrderDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetail> rt = cq.from(OrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
