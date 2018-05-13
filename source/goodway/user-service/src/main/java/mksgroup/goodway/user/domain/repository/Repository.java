/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.user.domain.repository;

/**
 * @author ThachLN
 * @param <TE>
 * @param <T>
 */
public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {

    /**
     * @param entity
     */
    void add(TE entity);

    /**
     * @param id
     */
    void remove(T id);

    /**
     * @param entity
     */
    void update(TE entity);
}
