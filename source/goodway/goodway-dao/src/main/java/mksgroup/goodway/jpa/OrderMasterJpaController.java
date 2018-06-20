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
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderDetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mksgroup.goodway.entity.DeliveryBatch;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.jpa.exceptions.IllegalOrphanException;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class OrderMasterJpaController implements Serializable {

    public OrderMasterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderMaster orderMaster) {
        if (orderMaster.getOrderDetailCollection() == null) {
            orderMaster.setOrderDetailCollection(new ArrayList<OrderDetail>());
        }
        if (orderMaster.getDeliveryBatchCollection() == null) {
            orderMaster.setDeliveryBatchCollection(new ArrayList<DeliveryBatch>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer customerId = orderMaster.getCustomerId();
            if (customerId != null) {
                customerId = em.getReference(customerId.getClass(), customerId.getId());
                orderMaster.setCustomerId(customerId);
            }
            Collection<OrderDetail> attachedOrderDetailCollection = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionOrderDetailToAttach : orderMaster.getOrderDetailCollection()) {
                orderDetailCollectionOrderDetailToAttach = em.getReference(orderDetailCollectionOrderDetailToAttach.getClass(), orderDetailCollectionOrderDetailToAttach.getId());
                attachedOrderDetailCollection.add(orderDetailCollectionOrderDetailToAttach);
            }
            orderMaster.setOrderDetailCollection(attachedOrderDetailCollection);
            Collection<DeliveryBatch> attachedDeliveryBatchCollection = new ArrayList<DeliveryBatch>();
            for (DeliveryBatch deliveryBatchCollectionDeliveryBatchToAttach : orderMaster.getDeliveryBatchCollection()) {
                deliveryBatchCollectionDeliveryBatchToAttach = em.getReference(deliveryBatchCollectionDeliveryBatchToAttach.getClass(), deliveryBatchCollectionDeliveryBatchToAttach.getId());
                attachedDeliveryBatchCollection.add(deliveryBatchCollectionDeliveryBatchToAttach);
            }
            orderMaster.setDeliveryBatchCollection(attachedDeliveryBatchCollection);
            em.persist(orderMaster);
            if (customerId != null) {
                customerId.getOrderMasterCollection().add(orderMaster);
                customerId = em.merge(customerId);
            }
            for (OrderDetail orderDetailCollectionOrderDetail : orderMaster.getOrderDetailCollection()) {
                OrderMaster oldOrderMasterIdOfOrderDetailCollectionOrderDetail = orderDetailCollectionOrderDetail.getOrderMasterId();
                orderDetailCollectionOrderDetail.setOrderMasterId(orderMaster);
                orderDetailCollectionOrderDetail = em.merge(orderDetailCollectionOrderDetail);
                if (oldOrderMasterIdOfOrderDetailCollectionOrderDetail != null) {
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionOrderDetail);
                    oldOrderMasterIdOfOrderDetailCollectionOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionOrderDetail);
                }
            }
            for (DeliveryBatch deliveryBatchCollectionDeliveryBatch : orderMaster.getDeliveryBatchCollection()) {
                OrderMaster oldOrderIdOfDeliveryBatchCollectionDeliveryBatch = deliveryBatchCollectionDeliveryBatch.getOrderId();
                deliveryBatchCollectionDeliveryBatch.setOrderId(orderMaster);
                deliveryBatchCollectionDeliveryBatch = em.merge(deliveryBatchCollectionDeliveryBatch);
                if (oldOrderIdOfDeliveryBatchCollectionDeliveryBatch != null) {
                    oldOrderIdOfDeliveryBatchCollectionDeliveryBatch.getDeliveryBatchCollection().remove(deliveryBatchCollectionDeliveryBatch);
                    oldOrderIdOfDeliveryBatchCollectionDeliveryBatch = em.merge(oldOrderIdOfDeliveryBatchCollectionDeliveryBatch);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderMaster orderMaster) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrderMaster persistentOrderMaster = em.find(OrderMaster.class, orderMaster.getId());
            Customer customerIdOld = persistentOrderMaster.getCustomerId();
            Customer customerIdNew = orderMaster.getCustomerId();
            Collection<OrderDetail> orderDetailCollectionOld = persistentOrderMaster.getOrderDetailCollection();
            Collection<OrderDetail> orderDetailCollectionNew = orderMaster.getOrderDetailCollection();
            Collection<DeliveryBatch> deliveryBatchCollectionOld = persistentOrderMaster.getDeliveryBatchCollection();
            Collection<DeliveryBatch> deliveryBatchCollectionNew = orderMaster.getDeliveryBatchCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderDetail orderDetailCollectionOldOrderDetail : orderDetailCollectionOld) {
                if (!orderDetailCollectionNew.contains(orderDetailCollectionOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailCollectionOldOrderDetail + " since its orderMasterId field is not nullable.");
                }
            }
            for (DeliveryBatch deliveryBatchCollectionOldDeliveryBatch : deliveryBatchCollectionOld) {
                if (!deliveryBatchCollectionNew.contains(deliveryBatchCollectionOldDeliveryBatch)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DeliveryBatch " + deliveryBatchCollectionOldDeliveryBatch + " since its orderId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (customerIdNew != null) {
                customerIdNew = em.getReference(customerIdNew.getClass(), customerIdNew.getId());
                orderMaster.setCustomerId(customerIdNew);
            }
            Collection<OrderDetail> attachedOrderDetailCollectionNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailCollectionNewOrderDetailToAttach : orderDetailCollectionNew) {
                orderDetailCollectionNewOrderDetailToAttach = em.getReference(orderDetailCollectionNewOrderDetailToAttach.getClass(), orderDetailCollectionNewOrderDetailToAttach.getId());
                attachedOrderDetailCollectionNew.add(orderDetailCollectionNewOrderDetailToAttach);
            }
            orderDetailCollectionNew = attachedOrderDetailCollectionNew;
            orderMaster.setOrderDetailCollection(orderDetailCollectionNew);
            Collection<DeliveryBatch> attachedDeliveryBatchCollectionNew = new ArrayList<DeliveryBatch>();
            for (DeliveryBatch deliveryBatchCollectionNewDeliveryBatchToAttach : deliveryBatchCollectionNew) {
                deliveryBatchCollectionNewDeliveryBatchToAttach = em.getReference(deliveryBatchCollectionNewDeliveryBatchToAttach.getClass(), deliveryBatchCollectionNewDeliveryBatchToAttach.getId());
                attachedDeliveryBatchCollectionNew.add(deliveryBatchCollectionNewDeliveryBatchToAttach);
            }
            deliveryBatchCollectionNew = attachedDeliveryBatchCollectionNew;
            orderMaster.setDeliveryBatchCollection(deliveryBatchCollectionNew);
            orderMaster = em.merge(orderMaster);
            if (customerIdOld != null && !customerIdOld.equals(customerIdNew)) {
                customerIdOld.getOrderMasterCollection().remove(orderMaster);
                customerIdOld = em.merge(customerIdOld);
            }
            if (customerIdNew != null && !customerIdNew.equals(customerIdOld)) {
                customerIdNew.getOrderMasterCollection().add(orderMaster);
                customerIdNew = em.merge(customerIdNew);
            }
            for (OrderDetail orderDetailCollectionNewOrderDetail : orderDetailCollectionNew) {
                if (!orderDetailCollectionOld.contains(orderDetailCollectionNewOrderDetail)) {
                    OrderMaster oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = orderDetailCollectionNewOrderDetail.getOrderMasterId();
                    orderDetailCollectionNewOrderDetail.setOrderMasterId(orderMaster);
                    orderDetailCollectionNewOrderDetail = em.merge(orderDetailCollectionNewOrderDetail);
                    if (oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail != null && !oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.equals(orderMaster)) {
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail.getOrderDetailCollection().remove(orderDetailCollectionNewOrderDetail);
                        oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail = em.merge(oldOrderMasterIdOfOrderDetailCollectionNewOrderDetail);
                    }
                }
            }
            for (DeliveryBatch deliveryBatchCollectionNewDeliveryBatch : deliveryBatchCollectionNew) {
                if (!deliveryBatchCollectionOld.contains(deliveryBatchCollectionNewDeliveryBatch)) {
                    OrderMaster oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch = deliveryBatchCollectionNewDeliveryBatch.getOrderId();
                    deliveryBatchCollectionNewDeliveryBatch.setOrderId(orderMaster);
                    deliveryBatchCollectionNewDeliveryBatch = em.merge(deliveryBatchCollectionNewDeliveryBatch);
                    if (oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch != null && !oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch.equals(orderMaster)) {
                        oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch.getDeliveryBatchCollection().remove(deliveryBatchCollectionNewDeliveryBatch);
                        oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch = em.merge(oldOrderIdOfDeliveryBatchCollectionNewDeliveryBatch);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderMaster.getId();
                if (findOrderMaster(id) == null) {
                    throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.");
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
            OrderMaster orderMaster;
            try {
                orderMaster = em.getReference(OrderMaster.class, id);
                orderMaster.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderMaster with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderDetail> orderDetailCollectionOrphanCheck = orderMaster.getOrderDetailCollection();
            for (OrderDetail orderDetailCollectionOrphanCheckOrderDetail : orderDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrderMaster (" + orderMaster + ") cannot be destroyed since the OrderDetail " + orderDetailCollectionOrphanCheckOrderDetail + " in its orderDetailCollection field has a non-nullable orderMasterId field.");
            }
            Collection<DeliveryBatch> deliveryBatchCollectionOrphanCheck = orderMaster.getDeliveryBatchCollection();
            for (DeliveryBatch deliveryBatchCollectionOrphanCheckDeliveryBatch : deliveryBatchCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrderMaster (" + orderMaster + ") cannot be destroyed since the DeliveryBatch " + deliveryBatchCollectionOrphanCheckDeliveryBatch + " in its deliveryBatchCollection field has a non-nullable orderId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Customer customerId = orderMaster.getCustomerId();
            if (customerId != null) {
                customerId.getOrderMasterCollection().remove(orderMaster);
                customerId = em.merge(customerId);
            }
            em.remove(orderMaster);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderMaster> findOrderMasterEntities() {
        return findOrderMasterEntities(true, -1, -1);
    }

    public List<OrderMaster> findOrderMasterEntities(int maxResults, int firstResult) {
        return findOrderMasterEntities(false, maxResults, firstResult);
    }

    private List<OrderMaster> findOrderMasterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderMaster.class));
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

    public OrderMaster findOrderMaster(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderMaster.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderMasterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderMaster> rt = cq.from(OrderMaster.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
