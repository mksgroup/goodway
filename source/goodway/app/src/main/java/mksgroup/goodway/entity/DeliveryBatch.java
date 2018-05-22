/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Danh sách các đợt giao hàng.
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_delivery_batch")
public class DeliveryBatch implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** Batch name. */
	@Column(name = "name", length = 1024, nullable = false)
	private String name;
    
    @Column(name = "address_id", nullable = false)
    @ElementCollection()
    @CollectionTable(name = "goodway_db_address")
    private List<Long> addressIds;

    /**
    * Get value of id.
    * @return the id
    */
    public Long getId() {
        return id;
    }

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * Get value of name.
    * @return the name
    */
    public String getName() {
        return name;
    }

    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Get value of addressIds.
    * @return the addressIds
    */
    public List<Long> getAddressIds() {
        return addressIds;
    }

    /**
     * Set the value for addressIds.
     * @param addressIds the addressIds to set
     */
    public void setAddressIds(List<Long> addressIds) {
        this.addressIds = addressIds;
    }
}
