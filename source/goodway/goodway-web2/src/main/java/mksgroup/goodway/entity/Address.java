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
@Table(name = "goodway_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id")
    , @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city")
    , @NamedQuery(name = "Address.findByDistrict", query = "SELECT a FROM Address a WHERE a.district = :district")
    , @NamedQuery(name = "Address.findByWard", query = "SELECT a FROM Address a WHERE a.ward = :ward")
    , @NamedQuery(name = "Address.findByUnit", query = "SELECT a FROM Address a WHERE a.unit = :unit")
    , @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
    , @NamedQuery(name = "Address.findByHouseNo", query = "SELECT a FROM Address a WHERE a.houseNo = :houseNo")
    , @NamedQuery(name = "Address.findByDisplayAddress", query = "SELECT a FROM Address a WHERE a.displayAddress = :displayAddress")
    , @NamedQuery(name = "Address.findByEnabled", query = "SELECT a FROM Address a WHERE a.enabled = :enabled")
    , @NamedQuery(name = "Address.findByLatitude", query = "SELECT a FROM Address a WHERE a.latitude = :latitude")
    , @NamedQuery(name = "Address.findByLongitude", query = "SELECT a FROM Address a WHERE a.longitude = :longitude")
    , @NamedQuery(name = "Address.findByCreated", query = "SELECT a FROM Address a WHERE a.created = :created")
    , @NamedQuery(name = "Address.findByCreatedbyUsername", query = "SELECT a FROM Address a WHERE a.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "Address.findByLastmodified", query = "SELECT a FROM Address a WHERE a.lastmodified = :lastmodified")
    , @NamedQuery(name = "Address.findByLastmodifiedbyUsername", query = "SELECT a FROM Address a WHERE a.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "district", length = 20)
    private String district;
    @Column(name = "ward", length = 50)
    private String ward;
    @Column(name = "unit", length = 50)
    private String unit;
    @Column(name = "street", length = 128)
    private String street;
    @Column(name = "house_no", length = 50)
    private String houseNo;
    @Column(name = "display_address", length = 512)
    private String displayAddress;
    @Column(name = "enabled")
    private Boolean enabled;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude", precision = 22)
    private Double latitude;
    @Column(name = "longitude", precision = 22)
    private Double longitude;
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

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Integer id, Date created, String createdbyUsername) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(String displayAddress) {
        this.displayAddress = displayAddress;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.Address[ id=" + id + " ]";
    }
    
}
