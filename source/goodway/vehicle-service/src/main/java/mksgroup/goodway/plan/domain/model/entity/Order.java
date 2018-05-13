/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.plan.domain.model.entity;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ThachLN
 */
public class Order extends BaseEntity<BigInteger> {

    /** Danh sách sản phẩm cần giao. */
    private List<Product> products;

    /** Địa chỉ giao hàng . */
    private String address;

    /** Vĩ độ của địa chỉ giao hàng. */
    private double latitude;

    /** Kinh độ của địa chỉ giao hàng. */
    private double longitude;
    /**
     * Get value of products.
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the value for products.
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Get value of address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value for address.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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

    /**
     * @param name
     * @param id
     * @param capacity
     */
    public Order(@JsonProperty("name") String name, @JsonProperty("id") BigInteger id,
            @JsonProperty("address") String address) {
        super(id, name);
        this.address = address;
    }

    /**
     * Overridden toString() method that return String presentation of the Object
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, capacity: %s}", this.getId(), this.getName(), this.getAddress());
    }

}
