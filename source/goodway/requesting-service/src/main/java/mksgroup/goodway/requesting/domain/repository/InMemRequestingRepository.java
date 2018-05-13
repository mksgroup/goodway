/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.domain.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

import mksgroup.goodway.requesting.domain.model.entity.Requesting;

/**
 * @author ThachLN
 */
@Repository("requestingRepository")
public class InMemRequestingRepository implements RequestingRepository<Requesting, String> {

    private Map<String, Requesting> entities;

    /**
     * Initialize the in-memory Requesting Repository with sample Map
     */
    public InMemRequestingRepository() {
        entities = new HashMap();
        Requesting requesting = new Requesting("1", "Requesting 1", "1", "1", "1", LocalDate.now(), LocalTime.now());
        entities.put("1", requesting);
        Requesting request2 = new Requesting("2", "Requesting 2", "2", "2", "2", LocalDate.now(), LocalTime.now());
        entities.put("2", request2);
    }

    /**
     * Check if given requesting name already exist.
     * @param name
     * @return true if already exist, else false
     */
    @Override
    public boolean containsName(String name) {
        try {
            return this.findByName(name).size() > 0;
        } catch (Exception ex) {
            // Exception Handler
        }
        return false;
    }

    /**
     * @param entity
     */
    @Override
    public void add(Requesting entity) {
        entities.put(entity.getId(), entity);
    }

    /**
     * @param id
     */
    @Override
    public void remove(String id) {
        if (entities.containsKey(id)) {
            entities.remove(id);
        }
    }

    /**
     * @param entity
     */
    @Override
    public void update(Requesting entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Requesting get(String id) {
        return entities.get(id);
    }

    /**
     * @return
     */
    @Override
    public Collection<Requesting> getAll() {
        return entities.values();
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Requesting> findByName(String name) throws Exception {
        Collection<Requesting> requestings = new ArrayList();
        int noOfChars = name.length();

        entities.forEach((k, v) -> {
            if (v.getName().toLowerCase().contains(name.toLowerCase().subSequence(0, noOfChars))) {
                requestings.add(v);
            }
        });

        return requestings;
    }

}
