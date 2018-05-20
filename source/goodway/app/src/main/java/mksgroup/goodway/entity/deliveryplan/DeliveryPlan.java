package mksgroup.goodway.entity.deliveryplan;

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
 * Entity chứa thông tin phương án giao hàng
 * @author ThachLN
 *
 */
@Entity
@Table(name = "goodway_dp")
public class DeliveryPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** Input: danh sách ID của các đơn hàng. */
    @Column(name = "order_id", nullable = false)
    @ElementCollection
    @CollectionTable(name = "goodway_dp_order")
    private List<Long> orderIds;
    
    /** Input: danh sách ID của các xe giao hàng. */
    @Column(name = "vehicle_id", nullable = false)
    @ElementCollection()
    @CollectionTable(name = "goodway_dp_vehicle")
    private List<Long> vehicleIds;
    
    /** Trạng thái xử lý:
     *  0 Chưa xử lý. Lúc này biến clusterIds bằng null.
     *  1 Đang xử lý. Kết quả tạm thời tra trong biến clusterIds
     *  2 Kế thúc xử lý. 
     *  */
    @Column
    private int status = 0;
    
    /** Output: sanh sách ID của các cụm giao hàng. */
    @Column(name = "cluster_id", nullable = false)
    @ElementCollection
    @CollectionTable(name = "goodway_dp_cluster")
    private List<Long> clusterIds;

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
    * Get value of orderIds.
    * @return the orderIds
    */
    public List<Long> getOrderIds() {
        return orderIds;
    }

    /**
     * Set the value for orderIds.
     * @param orderIds the orderIds to set
     */
    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    /**
    * Get value of vehicleIds.
    * @return the vehicleIds
    */
    public List<Long> getVehicleIds() {
        return vehicleIds;
    }

    /**
     * Set the value for vehicleIds.
     * @param vehicleIds the vehicleIds to set
     */
    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    /**
    * Get value of status.
    * @return the status
    */
    public int getStatus() {
        return status;
    }

    /**
     * Set the value for status.
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
    * Get value of clusterIds.
    * @return the clusterIds
    */
    public List<Long> getClusterIds() {
        return clusterIds;
    }

    /**
     * Set the value for clusterIds.
     * @param clusterIds the clusterIds to set
     */
    public void setClusterIds(List<Long> clusterIds) {
        this.clusterIds = clusterIds;
    }

      
}
