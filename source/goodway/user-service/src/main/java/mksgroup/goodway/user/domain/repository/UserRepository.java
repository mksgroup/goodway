/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.user.domain.repository;

import java.util.Collection;

/**
 * @author ThachLN
 * @param <User>
 * @param <String>
 */
public interface UserRepository<Booking, String> extends Repository<Booking, String> {

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
    public Collection<Booking> findByName(String name) throws Exception;
}
