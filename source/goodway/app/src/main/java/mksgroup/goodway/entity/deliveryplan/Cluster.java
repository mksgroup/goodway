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
 * Mô tả một cụm giao hàng.
 * <br/>
 * Entity này được tra từ entity {@link mksgroup.goodway.entity.deliveryplan.DeliveryPlan}.
 * @author ThachLN
 *
 */
@Entity
@Table(name = "goodway_dp_cluster")
public class Cluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    /** Danh sách các đơn hàng trong một cụm. */
    @Column(name = "order_id", nullable = false)
    @ElementCollection
    @CollectionTable(name = "goodway_cluster_order")
    private List<Long> orderIds;
}
