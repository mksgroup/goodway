package mksgroup.goodway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goodway_vehicle")
public class Vehicle implements Serializable {
    /**
     * @param name
     * @param length
     * @param width
     * @param height
     * @param capacity
     */
    public Vehicle(String name, double length, double width, double height, double capacity) {
        super();
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.capacity = capacity;
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = 1771888441368721319L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /** Thể tích có thể vận chuyển. Đơn vị m*/
    /** Thể tích đã đóng gói. length × width × height. Đơn vị mỗi cạnh là m.*/
    @Column(name = "height", nullable = false)
    private double height;
    @Column(name = "width", nullable = false)
    private double width;
    @Column(name = "length", nullable = false)
    private double length;

    /** Loading capacity: tải trọng có thể vận chuyển. Đơn vị tấn */
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
    * Get value of height.
    * @return the height
    */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value for height.
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
    * Get value of width.
    * @return the width
    */
    public double getWidth() {
        return width;
    }

    /**
     * Set the value for width.
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
    * Get value of length.
    * @return the length
    */
    public double getLength() {
        return length;
    }

    /**
     * Set the value for length.
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
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
