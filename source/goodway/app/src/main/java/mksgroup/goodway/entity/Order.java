/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Đơn hàng cần phải giao.
 * @author ThachLN
 */
public class Order implements Serializable {
    private Integer id;

    private String name;

    /** Ngày lập đơn hàng. */
    private Date createdDate;

    /** Ngày dự kiến giao đơn hàng . */
    private Date deliveryDate;

    /** Danh sách sản phẩm cần giao. */
    private List<Product> products;

    /** Địa chỉ giao hàng . */
    private Address address;

    /**
    * Get value of id.
    * @return the id
    */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Integer id) {
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
    * Get value of createdDate.
    * @return the createdDate
    */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Set the value for createdDate.
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
    * Get value of deliveryDate.
    * @return the deliveryDate
    */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Set the value for deliveryDate.
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

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
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value for address.
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}
