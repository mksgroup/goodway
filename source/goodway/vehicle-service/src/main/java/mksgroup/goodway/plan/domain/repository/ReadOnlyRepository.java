/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan.domain.repository;

import java.util.Collection;

/**
 * @author ThachLN
 * @param <TE>
 * @param <T>
 */
public interface ReadOnlyRepository<TE, T> {

    // long Count;
    /**
     * @param id
     * @return
     */
    boolean contains(T id);

    /**
     * @param id
     * @return
     */
    TE get(T id);

    /**
     * @return
     */
    Collection<TE> getAll();
}
