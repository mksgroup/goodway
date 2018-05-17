/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 *
 * @author ThachLN
 */
public class Product extends BaseEntity<BigInteger> {

    /** Thể tích đã đóng gói. Đơn vị tính là cm^2.*/
    private double volume;
    
    /** Trọng lượng đã đóng gói. Đơn vị tính là gram. */
    private double weight;
    /**
     *
     * @param name
     * @param id
     * @param capacity
     */
    public Product(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id, @JsonProperty("volume") double volume, @JsonProperty("weight") double weight) {
        super(id, name);
        this.volume = volume;
        this.weight = weight;
    }

    /**
    * Get value of volume.
    * @return the volume
    */
    public double getVolume() {
        return volume;
    }

    /**
     * Set the value for volume.
     * @param volume the volume to set
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }


    /**
    * Get value of weight.
    * @return the weight
    */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the value for weight.
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, volume: %s}",
                this.getId(), this.getName(), this.getVolume());
    }

}
