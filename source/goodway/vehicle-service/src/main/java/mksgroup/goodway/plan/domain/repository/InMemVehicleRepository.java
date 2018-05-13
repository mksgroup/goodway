package mksgroup.goodway.plan.domain.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import mksgroup.goodway.plan.domain.model.entity.Vehicle;

/**
 *
 * @author ThachLN
 */
@Repository("VehicleRepository")
public class InMemVehicleRepository implements VehicleRepository<Vehicle, String> {

    private Map<String, Vehicle> entities;

    /**
     * Initialize the in-memory Vehicle Repository with empty Map
     */
    public InMemVehicleRepository() {
        entities = new HashMap();
        Vehicle Vehicle = new Vehicle("Xe 1", "1", 100.0, 100.0);
        entities.put("1", Vehicle);
        Vehicle = new Vehicle("Xe 2", "2", 150, 150);
        entities.put("2", Vehicle);

    }

    /**
     * Check if given Vehicle name already exist.
     *
     * @param name
     * @return true if already exist, else false
     */
    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void add(Vehicle entity) {
        entities.put(entity.getId(), entity);
    }

    /**
     *
     * @param id
     */
    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    /**
     *
     * @param entity
     */
    @Override
    public void update(Vehicle entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Vehicle get(String id) {
        return entities.get(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Vehicle> getAll() {
        return entities.values();
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Vehicle> findByName(String name) throws Exception {
        Collection<Vehicle> Vehicles = new ArrayList();
        int noOfChars = name.length();
        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.subSequence(0, noOfChars))) {
                Vehicles.add(v);
            }
        });
        return Vehicles;
    }

}
