/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.user.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import mksgroup.goodway.user.domain.model.entity.Entity;
import mksgroup.goodway.user.domain.model.entity.User;

/**
 * @author ThachLN
 */
public interface UserService {

    /**
     * @param reequestor
     * @throws Exception
     */
    public void add(User reequestor) throws Exception;

    /**
     * @param reequestor
     * @throws Exception
     */
    public void update(User reequestor) throws Exception;

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
    public Collection<User> findByName(String name) throws Exception;

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
