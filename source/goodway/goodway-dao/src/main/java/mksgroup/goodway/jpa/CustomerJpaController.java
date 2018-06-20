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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.jpa.exceptions.IllegalOrphanException;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) {
        if (customer.getOrderMasterCollection() == null) {
            customer.setOrderMasterCollection(new ArrayList<OrderMaster>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<OrderMaster> attachedOrderMasterCollection = new ArrayList<OrderMaster>();
            for (OrderMaster orderMasterCollectionOrderMasterToAttach : customer.getOrderMasterCollection()) {
                orderMasterCollectionOrderMasterToAttach = em.getReference(orderMasterCollectionOrderMasterToAttach.getClass(), orderMasterCollectionOrderMasterToAttach.getId());
                attachedOrderMasterCollection.add(orderMasterCollectionOrderMasterToAttach);
            }
            customer.setOrderMasterCollection(attachedOrderMasterCollection);
            em.persist(customer);
            for (OrderMaster orderMasterCollectionOrderMaster : customer.getOrderMasterCollection()) {
                Customer oldCustomerIdOfOrderMasterCollectionOrderMaster = orderMasterCollectionOrderMaster.getCustomerId();
                orderMasterCollectionOrderMaster.setCustomerId(customer);
                orderMasterCollectionOrderMaster = em.merge(orderMasterCollectionOrderMaster);
                if (oldCustomerIdOfOrderMasterCollectionOrderMaster != null) {
                    oldCustomerIdOfOrderMasterCollectionOrderMaster.getOrderMasterCollection().remove(orderMasterCollectionOrderMaster);
                    oldCustomerIdOfOrderMasterCollectionOrderMaster = em.merge(oldCustomerIdOfOrderMasterCollectionOrderMaster);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getId());
            Collection<OrderMaster> orderMasterCollectionOld = persistentCustomer.getOrderMasterCollection();
            Collection<OrderMaster> orderMasterCollectionNew = customer.getOrderMasterCollection();
            List<String> illegalOrphanMessages = null;
            for (OrderMaster orderMasterCollectionOldOrderMaster : orderMasterCollectionOld) {
                if (!orderMasterCollectionNew.contains(orderMasterCollectionOldOrderMaster)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderMaster " + orderMasterCollectionOldOrderMaster + " since its customerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<OrderMaster> attachedOrderMasterCollectionNew = new ArrayList<OrderMaster>();
            for (OrderMaster orderMasterCollectionNewOrderMasterToAttach : orderMasterCollectionNew) {
                orderMasterCollectionNewOrderMasterToAttach = em.getReference(orderMasterCollectionNewOrderMasterToAttach.getClass(), orderMasterCollectionNewOrderMasterToAttach.getId());
                attachedOrderMasterCollectionNew.add(orderMasterCollectionNewOrderMasterToAttach);
            }
            orderMasterCollectionNew = attachedOrderMasterCollectionNew;
            customer.setOrderMasterCollection(orderMasterCollectionNew);
            customer = em.merge(customer);
            for (OrderMaster orderMasterCollectionNewOrderMaster : orderMasterCollectionNew) {
                if (!orderMasterCollectionOld.contains(orderMasterCollectionNewOrderMaster)) {
                    Customer oldCustomerIdOfOrderMasterCollectionNewOrderMaster = orderMasterCollectionNewOrderMaster.getCustomerId();
                    orderMasterCollectionNewOrderMaster.setCustomerId(customer);
                    orderMasterCollectionNewOrderMaster = em.merge(orderMasterCollectionNewOrderMaster);
                    if (oldCustomerIdOfOrderMasterCollectionNewOrderMaster != null && !oldCustomerIdOfOrderMasterCollectionNewOrderMaster.equals(customer)) {
                        oldCustomerIdOfOrderMasterCollectionNewOrderMaster.getOrderMasterCollection().remove(orderMasterCollectionNewOrderMaster);
                        oldCustomerIdOfOrderMasterCollectionNewOrderMaster = em.merge(oldCustomerIdOfOrderMasterCollectionNewOrderMaster);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customer.getId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<OrderMaster> orderMasterCollectionOrphanCheck = customer.getOrderMasterCollection();
            for (OrderMaster orderMasterCollectionOrphanCheckOrderMaster : orderMasterCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the OrderMaster " + orderMasterCollectionOrphanCheckOrderMaster + " in its orderMasterCollection field has a non-nullable customerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
