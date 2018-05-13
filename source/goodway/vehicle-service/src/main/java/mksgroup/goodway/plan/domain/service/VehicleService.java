/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import mksgroup.goodway.plan.domain.model.entity.Entity;
import mksgroup.goodway.plan.domain.model.entity.Vehicle;

/**
 *
 * @author ThachLN
 */
public interface VehicleService {

    /**
     *
     * @param vehicle
     * @throws Exception
     */
    public void add(Vehicle vehicle) throws Exception;

    /**
     *
     * @param vehicle
     * @throws Exception
     */
    public void update(Vehicle vehicle) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public Entity findById(String orderId) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Vehicle> findByName(String name) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Vehicle> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
