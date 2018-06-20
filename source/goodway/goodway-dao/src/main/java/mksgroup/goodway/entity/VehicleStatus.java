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
@Table(name = "goodway_vehicle_status", catalog = "goodway", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VehicleStatus.findAll", query = "SELECT v FROM VehicleStatus v")
    , @NamedQuery(name = "VehicleStatus.findById", query = "SELECT v FROM VehicleStatus v WHERE v.id = :id")
    , @NamedQuery(name = "VehicleStatus.findByStartTime", query = "SELECT v FROM VehicleStatus v WHERE v.startTime = :startTime")
    , @NamedQuery(name = "VehicleStatus.findByEndTime", query = "SELECT v FROM VehicleStatus v WHERE v.endTime = :endTime")
    , @NamedQuery(name = "VehicleStatus.findByStatus", query = "SELECT v FROM VehicleStatus v WHERE v.status = :status")
    , @NamedQuery(name = "VehicleStatus.findByCreated", query = "SELECT v FROM VehicleStatus v WHERE v.created = :created")
    , @NamedQuery(name = "VehicleStatus.findByCreatedbyUsername", query = "SELECT v FROM VehicleStatus v WHERE v.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "VehicleStatus.findByLastmodified", query = "SELECT v FROM VehicleStatus v WHERE v.lastmodified = :lastmodified")
    , @NamedQuery(name = "VehicleStatus.findByLastmodifiedbyUsername", query = "SELECT v FROM VehicleStatus v WHERE v.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class VehicleStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "status")
    private Integer status;
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
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Vehicle vehicleId;

    public VehicleStatus() {
    }

    public VehicleStatus(Integer id) {
        this.id = id;
    }

    public VehicleStatus(Integer id, Date created, String createdbyUsername) {
        this.id = id;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
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
        if (!(object instanceof VehicleStatus)) {
            return false;
        }
        VehicleStatus other = (VehicleStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.VehicleStatus[ id=" + id + " ]";
    }
    
}
