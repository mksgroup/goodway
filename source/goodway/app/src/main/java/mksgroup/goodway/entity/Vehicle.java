package mksgroup.goodway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goodway_vehicle")
public class Vehicle implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1771888441368721319L;

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /** Thể tích có thể vận chuyển. Đơn vị cm^2 */
    @Column(name = "volume", nullable = false)
    private double volume;

    /** Loading capacity: tải trọng có thể vận chuyển. */
    @Column(name = "capacity", nullable = false)
    private double capacity;

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
