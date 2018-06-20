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
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.entity.VehicleStatus;
import mksgroup.goodway.jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author ThachLN
 */
public class VehicleStatusJpaController implements Serializable {

    public VehicleStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VehicleStatus vehicleStatus) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehicle vehicleId = vehicleStatus.getVehicleId();
            if (vehicleId != null) {
                vehicleId = em.getReference(vehicleId.getClass(), vehicleId.getId());
                vehicleStatus.setVehicleId(vehicleId);
            }
            em.persist(vehicleStatus);
            if (vehicleId != null) {
                vehicleId.getVehicleStatusCollection().add(vehicleStatus);
                vehicleId = em.merge(vehicleId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VehicleStatus vehicleStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VehicleStatus persistentVehicleStatus = em.find(VehicleStatus.class, vehicleStatus.getId());
            Vehicle vehicleIdOld = persistentVehicleStatus.getVehicleId();
            Vehicle vehicleIdNew = vehicleStatus.getVehicleId();
            if (vehicleIdNew != null) {
                vehicleIdNew = em.getReference(vehicleIdNew.getClass(), vehicleIdNew.getId());
                vehicleStatus.setVehicleId(vehicleIdNew);
            }
            vehicleStatus = em.merge(vehicleStatus);
            if (vehicleIdOld != null && !vehicleIdOld.equals(vehicleIdNew)) {
                vehicleIdOld.getVehicleStatusCollection().remove(vehicleStatus);
                vehicleIdOld = em.merge(vehicleIdOld);
            }
            if (vehicleIdNew != null && !vehicleIdNew.equals(vehicleIdOld)) {
                vehicleIdNew.getVehicleStatusCollection().add(vehicleStatus);
                vehicleIdNew = em.merge(vehicleIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehicleStatus.getId();
                if (findVehicleStatus(id) == null) {
                    throw new NonexistentEntityException("The vehicleStatus with id " + id + " no longer exists.");
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
            VehicleStatus vehicleStatus;
            try {
                vehicleStatus = em.getReference(VehicleStatus.class, id);
                vehicleStatus.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehicleStatus with id " + id + " no longer exists.", enfe);
            }
            Vehicle vehicleId = vehicleStatus.getVehicleId();
            if (vehicleId != null) {
                vehicleId.getVehicleStatusCollection().remove(vehicleStatus);
                vehicleId = em.merge(vehicleId);
            }
            em.remove(vehicleStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VehicleStatus> findVehicleStatusEntities() {
        return findVehicleStatusEntities(true, -1, -1);
    }

    public List<VehicleStatus> findVehicleStatusEntities(int maxResults, int firstResult) {
        return findVehicleStatusEntities(false, maxResults, firstResult);
    }

    private List<VehicleStatus> findVehicleStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VehicleStatus.class));
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

    public VehicleStatus findVehicleStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VehicleStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehicleStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VehicleStatus> rt = cq.from(VehicleStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
