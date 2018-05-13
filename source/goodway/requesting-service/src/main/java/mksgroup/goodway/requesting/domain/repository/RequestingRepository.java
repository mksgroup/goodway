/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.domain.repository;

import java.util.Collection;

/**
 * @author ThachLN
 * @param <Requesting>
 * @param <String>
 */
public interface RequestingRepository<Requesting, String> extends Repository<Requesting, String> {

    /**
     * @param name
     * @return
     */
    boolean containsName(String name);

    /**
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Requesting> findByName(String name) throws Exception;
}
