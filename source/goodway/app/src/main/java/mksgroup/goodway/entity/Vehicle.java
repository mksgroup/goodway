package mksgroup.goodway.entity;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private Integer id;

    private String name;

    /** Thể tích có thể vận chuyển. Đơn vị cm^2 */
    private double volume;

    /** Loading capacity: tải trọng có thể vận chuyển. */
    private double capacity;

    /**
     * Get value of id.
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Integer id) {
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

}
