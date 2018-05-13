/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan.domain.model.entity;

/**
 * @author ThachLN
 * @param <T>
 */
public abstract class BaseEntity<T> extends Entity<T> {

    private boolean isModified;

    /**
     * @param id
     * @param name
     */
    public BaseEntity(T id, String name) {
        super.id = id;
        super.name = name;
        isModified = false;
    }

    /**
     * @return
     */
    public boolean isIsModified() {
        return isModified;
    }

}
