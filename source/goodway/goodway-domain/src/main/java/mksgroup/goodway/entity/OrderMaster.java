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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "goodway_order_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderMaster.findAll", query = "SELECT o FROM OrderMaster o")
    , @NamedQuery(name = "OrderMaster.findById", query = "SELECT o FROM OrderMaster o WHERE o.id = :id")
    , @NamedQuery(name = "OrderMaster.findByName", query = "SELECT o FROM OrderMaster o WHERE o.name = :name")
    , @NamedQuery(name = "OrderMaster.findByDeliveryDate", query = "SELECT o FROM OrderMaster o WHERE o.deliveryDate = :deliveryDate")
    , @NamedQuery(name = "OrderMaster.findByCreated", query = "SELECT o FROM OrderMaster o WHERE o.created = :created")
    , @NamedQuery(name = "OrderMaster.findByCreatedbyUsername", query = "SELECT o FROM OrderMaster o WHERE o.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "OrderMaster.findByLastmodified", query = "SELECT o FROM OrderMaster o WHERE o.lastmodified = :lastmodified")
    , @NamedQuery(name = "OrderMaster.findByLastmodifiedbyUsername", query = "SELECT o FROM OrderMaster o WHERE o.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class OrderMaster implements Serializable {

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
    @Column(name = "delivery_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
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

    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;

    public OrderMaster() {
    }

    public OrderMaster(Integer id) {
        this.id = id;
    }

    public OrderMaster(Integer id, String name, Date deliveryDate, Date created, String createdbyUsername) {
        this.id = id;
        this.name = name;
        this.deliveryDate = deliveryDate;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof OrderMaster)) {
            return false;
        }
        OrderMaster other = (OrderMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.OrderMaster[ id=" + id + " ]";
    }
    
}
