/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mô tả địa chỉ.
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_address")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4805764148216235679L;

	@Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    /** Thành phố. */
    private String city;
    /** Quận/huyện . */
    private String district;
    /** Phường . */
    private String ward;
    /** Khu phố/xã . */
    private String unit;

    /** Đường . */
    private String street;
    /** Số nhà . */
    private String no;

    /** Địa chỉ được viết gọn hoặc viết tắt. */
    private String displayAddress;

    /** Vĩ độ của địa chỉ giao hàng. */
    private double latitude;

    /** Kinh độ của địa chỉ giao hàng. */
    private double longitude;

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
    * Get value of city.
    * @return the city
    */
    public String getCity() {
        return city;
    }

    /**
     * Set the value for city.
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
    * Get value of district.
    * @return the district
    */
    public String getDistrict() {
        return district;
    }

    /**
     * Set the value for district.
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
    * Get value of ward.
    * @return the ward
    */
    public String getWard() {
        return ward;
    }

    /**
     * Set the value for ward.
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
    * Get value of unit.
    * @return the unit
    */
    public String getUnit() {
        return unit;
    }

    /**
     * Set the value for unit.
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
    * Get value of street.
    * @return the street
    */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value for street.
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
    * Get value of no.
    * @return the no
    */
    public String getNo() {
        return no;
    }

    /**
     * Set the value for no.
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
    * Get value of displayAddress.
    * @return the displayAddress
    */
    public String getDisplayAddress() {
        return displayAddress;
    }

    /**
     * Set the value for displayAddress.
     * @param displayAddress the displayAddress to set
     */
    public void setDisplayAddress(String displayAddress) {
        this.displayAddress = displayAddress;
    }

    /**
    * Get value of latitude.
    * @return the latitude
    */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value for latitude.
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
    * Get value of longitude.
    * @return the longitude
    */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value for longitude.
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
