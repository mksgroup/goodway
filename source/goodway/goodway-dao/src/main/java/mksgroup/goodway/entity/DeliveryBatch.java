/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_delivery_batch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryBatch.findAll", query = "SELECT d FROM DeliveryBatch d")
    , @NamedQuery(name = "DeliveryBatch.findById", query = "SELECT d FROM DeliveryBatch d WHERE d.id = :id")
    , @NamedQuery(name = "DeliveryBatch.findByName", query = "SELECT d FROM DeliveryBatch d WHERE d.name = :name")
    , @NamedQuery(name = "DeliveryBatch.findBySeqNo", query = "SELECT d FROM DeliveryBatch d WHERE d.seqNo = :seqNo")
    , @NamedQuery(name = "DeliveryBatch.findByStartTime", query = "SELECT d FROM DeliveryBatch d WHERE d.startTime = :startTime")
    , @NamedQuery(name = "DeliveryBatch.findByEndTime", query = "SELECT d FROM DeliveryBatch d WHERE d.endTime = :endTime")
    , @NamedQuery(name = "DeliveryBatch.findByFromLatitude", query = "SELECT d FROM DeliveryBatch d WHERE d.fromLatitude = :fromLatitude")
    , @NamedQuery(name = "DeliveryBatch.findByFromLongitude", query = "SELECT d FROM DeliveryBatch d WHERE d.fromLongitude = :fromLongitude")
    , @NamedQuery(name = "DeliveryBatch.findByToLatitude", query = "SELECT d FROM DeliveryBatch d WHERE d.toLatitude = :toLatitude")
    , @NamedQuery(name = "DeliveryBatch.findByToLongitude", query = "SELECT d FROM DeliveryBatch d WHERE d.toLongitude = :toLongitude")
    , @NamedQuery(name = "DeliveryBatch.findByDistance", query = "SELECT d FROM DeliveryBatch d WHERE d.distance = :distance")
    , @NamedQuery(name = "DeliveryBatch.findByCreated", query = "SELECT d FROM DeliveryBatch d WHERE d.created = :created")
    , @NamedQuery(name = "DeliveryBatch.findByCreatedbyUsername", query = "SELECT d FROM DeliveryBatch d WHERE d.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "DeliveryBatch.findByLastmodified", query = "SELECT d FROM DeliveryBatch d WHERE d.lastmodified = :lastmodified")
    , @NamedQuery(name = "DeliveryBatch.findByLastmodifiedbyUsername", query = "SELECT d FROM DeliveryBatch d WHERE d.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class DeliveryBatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    @Basic(optional = false)
    @Column(name = "seq_no", nullable = false)
    private int seqNo;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "from_latitude", precision = 22)
    private Double fromLatitude;
    @Column(name = "from_longitude", precision = 22)
    private Double fromLongitude;
    @Column(name = "to_latitude", precision = 22)
    private Double toLatitude;
    @Column(name = "to_longitude", precision = 22)
    private Double toLongitude;
    @Column(name = "distance", precision = 22)
    private Double distance;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "createdby_username", nullable = false, length = 50)
    private String createdbyUsername;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "lastmodifiedby_username", length = 50)
    private String lastmodifiedbyUsername;
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderMaster orderId;

    public DeliveryBatch() {
    }

    public DeliveryBatch(Integer id) {
        this.id = id;
    }

    public DeliveryBatch(Integer id, String name, int seqNo, Date created, String createdbyUsername) {
        this.id = id;
        this.name = name;
        this.seqNo = seqNo;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(Double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public Double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(Double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public Double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(Double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public Double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(Double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    public OrderMaster getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderMaster orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryBatch)) {
            return false;
        }
        DeliveryBatch other = (DeliveryBatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.DeliveryBatch[ id=" + id + " ]";
    }
    
}
