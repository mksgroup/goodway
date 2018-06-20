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
import mksgroup.goodway.entity.VehicleStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.jpa.exceptions.IllegalOrphanException;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class VehicleJpaController implements Serializable {

    public VehicleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehicle vehicle) {
        if (vehicle.getVehicleStatusCollection() == null) {
            vehicle.setVehicleStatusCollection(new ArrayList<VehicleStatus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<VehicleStatus> attachedVehicleStatusCollection = new ArrayList<VehicleStatus>();
            for (VehicleStatus vehicleStatusCollectionVehicleStatusToAttach : vehicle.getVehicleStatusCollection()) {
                vehicleStatusCollectionVehicleStatusToAttach = em.getReference(vehicleStatusCollectionVehicleStatusToAttach.getClass(), vehicleStatusCollectionVehicleStatusToAttach.getId());
                attachedVehicleStatusCollection.add(vehicleStatusCollectionVehicleStatusToAttach);
            }
            vehicle.setVehicleStatusCollection(attachedVehicleStatusCollection);
            em.persist(vehicle);
            for (VehicleStatus vehicleStatusCollectionVehicleStatus : vehicle.getVehicleStatusCollection()) {
                Vehicle oldVehicleIdOfVehicleStatusCollectionVehicleStatus = vehicleStatusCollectionVehicleStatus.getVehicleId();
                vehicleStatusCollectionVehicleStatus.setVehicleId(vehicle);
                vehicleStatusCollectionVehicleStatus = em.merge(vehicleStatusCollectionVehicleStatus);
                if (oldVehicleIdOfVehicleStatusCollectionVehicleStatus != null) {
                    oldVehicleIdOfVehicleStatusCollectionVehicleStatus.getVehicleStatusCollection().remove(vehicleStatusCollectionVehicleStatus);
                    oldVehicleIdOfVehicleStatusCollectionVehicleStatus = em.merge(oldVehicleIdOfVehicleStatusCollectionVehicleStatus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehicle vehicle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehicle persistentVehicle = em.find(Vehicle.class, vehicle.getId());
            Collection<VehicleStatus> vehicleStatusCollectionOld = persistentVehicle.getVehicleStatusCollection();
            Collection<VehicleStatus> vehicleStatusCollectionNew = vehicle.getVehicleStatusCollection();
            List<String> illegalOrphanMessages = null;
            for (VehicleStatus vehicleStatusCollectionOldVehicleStatus : vehicleStatusCollectionOld) {
                if (!vehicleStatusCollectionNew.contains(vehicleStatusCollectionOldVehicleStatus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VehicleStatus " + vehicleStatusCollectionOldVehicleStatus + " since its vehicleId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<VehicleStatus> attachedVehicleStatusCollectionNew = new ArrayList<VehicleStatus>();
            for (VehicleStatus vehicleStatusCollectionNewVehicleStatusToAttach : vehicleStatusCollectionNew) {
                vehicleStatusCollectionNewVehicleStatusToAttach = em.getReference(vehicleStatusCollectionNewVehicleStatusToAttach.getClass(), vehicleStatusCollectionNewVehicleStatusToAttach.getId());
                attachedVehicleStatusCollectionNew.add(vehicleStatusCollectionNewVehicleStatusToAttach);
            }
            vehicleStatusCollectionNew = attachedVehicleStatusCollectionNew;
            vehicle.setVehicleStatusCollection(vehicleStatusCollectionNew);
            vehicle = em.merge(vehicle);
            for (VehicleStatus vehicleStatusCollectionNewVehicleStatus : vehicleStatusCollectionNew) {
                if (!vehicleStatusCollectionOld.contains(vehicleStatusCollectionNewVehicleStatus)) {
                    Vehicle oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus = vehicleStatusCollectionNewVehicleStatus.getVehicleId();
                    vehicleStatusCollectionNewVehicleStatus.setVehicleId(vehicle);
                    vehicleStatusCollectionNewVehicleStatus = em.merge(vehicleStatusCollectionNewVehicleStatus);
                    if (oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus != null && !oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus.equals(vehicle)) {
                        oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus.getVehicleStatusCollection().remove(vehicleStatusCollectionNewVehicleStatus);
                        oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus = em.merge(oldVehicleIdOfVehicleStatusCollectionNewVehicleStatus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehicle.getId();
                if (findVehicle(id) == null) {
                    throw new NonexistentEntityException("The vehicle with id " + id + " no longer exists.");
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
            Vehicle vehicle;
            try {
                vehicle = em.getReference(Vehicle.class, id);
                vehicle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehicle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<VehicleStatus> vehicleStatusCollectionOrphanCheck = vehicle.getVehicleStatusCollection();
            for (VehicleStatus vehicleStatusCollectionOrphanCheckVehicleStatus : vehicleStatusCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehicle (" + vehicle + ") cannot be destroyed since the VehicleStatus " + vehicleStatusCollectionOrphanCheckVehicleStatus + " in its vehicleStatusCollection field has a non-nullable vehicleId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vehicle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehicle> findVehicleEntities() {
        return findVehicleEntities(true, -1, -1);
    }

    public List<Vehicle> findVehicleEntities(int maxResults, int firstResult) {
        return findVehicleEntities(false, maxResults, firstResult);
    }

    private List<Vehicle> findVehicleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehicle.class));
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

    public Vehicle findVehicle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehicle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehicleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehicle> rt = cq.from(Vehicle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
