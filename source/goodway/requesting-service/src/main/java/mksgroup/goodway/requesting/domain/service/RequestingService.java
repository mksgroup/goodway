/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import mksgroup.goodway.requesting.domain.model.entity.Requesting;
import mksgroup.goodway.requesting.domain.model.entity.Entity;

/**
 * @author ThachLN
 */
public interface RequestingService {

    /**
     * @param requesting
     * @throws Exception
     */
    public void add(Requesting requesting) throws Exception;

    /**
     * @param requesting
     * @throws Exception
     */
    public void update(Requesting requesting) throws Exception;

    /**
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(String id) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Requesting> findByName(String name) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Requesting> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
