/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_vehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")
    , @NamedQuery(name = "Vehicle.findById", query = "SELECT v FROM Vehicle v WHERE v.id = :id")
    , @NamedQuery(name = "Vehicle.findByName", query = "SELECT v FROM Vehicle v WHERE v.name = :name")
    , @NamedQuery(name = "Vehicle.findByHeight", query = "SELECT v FROM Vehicle v WHERE v.height = :height")
    , @NamedQuery(name = "Vehicle.findByWidth", query = "SELECT v FROM Vehicle v WHERE v.width = :width")
    , @NamedQuery(name = "Vehicle.findByLength", query = "SELECT v FROM Vehicle v WHERE v.length = :length")
    , @NamedQuery(name = "Vehicle.findByCapacity", query = "SELECT v FROM Vehicle v WHERE v.capacity = :capacity")
    , @NamedQuery(name = "Vehicle.findByQuantity", query = "SELECT v FROM Vehicle v WHERE v.quantity = :quantity")
    , @NamedQuery(name = "Vehicle.findByEnabled", query = "SELECT v FROM Vehicle v WHERE v.enabled = :enabled")
    , @NamedQuery(name = "Vehicle.findByCreated", query = "SELECT v FROM Vehicle v WHERE v.created = :created")
    , @NamedQuery(name = "Vehicle.findByCreatedbyUsername", query = "SELECT v FROM Vehicle v WHERE v.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "Vehicle.findByLastmodified", query = "SELECT v FROM Vehicle v WHERE v.lastmodified = :lastmodified")
    , @NamedQuery(name = "Vehicle.findByLastmodifiedbyUsername", query = "SELECT v FROM Vehicle v WHERE v.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "height", precision = 22)
    private Double height;
    @Column(name = "width", precision = 22)
    private Double width;
    @Column(name = "length", precision = 22)
    private Double length;
    @Column(name = "capacity", precision = 22)
    private Double capacity;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "enabled")
    private Boolean enabled;
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

    public Vehicle() {
    }

    public Vehicle(Integer id) {
        this.id = id;
    }

    public Vehicle(Integer id, String name, Date created, String createdbyUsername) {
        this.id = id;
        this.name = name;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.Vehicle[ id=" + id + " ]";
    }
    
}
