/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.vehicle.domain.model.entity;

/**
 * @author ThachLN
 */
public class Vehicle extends BaseEntity<String> implements Serialiable {

    /** Thể tích có thể vận chuyển. Đơn vị cm^2 */
    private double volume;

    /** Loading capacity: tải trọng có thể vận chuyển. */
    private double capacity;

    /**
     * Get value of volume.
     * @return the volume
     */
    public double getVolume() {
        return volume;
    }

    public Vehicle(String id, String name) {
        super(id, name);
    }

    /**
     * @param id
     * @param name
     * @param volume
     * @param capacity
     */
    public Vehicle(String id, String name, double volume, double capacity) {
        super(id, name);
        this.volume = volume;
        this.capacity = capacity;
    }

    /**
     * Set the value for volume.
     * @param volume the volume to set
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Get value of capacity.
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Set the value for capacity.
     * @param capacity the capacity to set
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Overridden toString() method that return String presentation of the Object
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, tables: %s}", this.getId(), this.getName(),
                this.getVolume(), this.getCapacity());
    }

}
