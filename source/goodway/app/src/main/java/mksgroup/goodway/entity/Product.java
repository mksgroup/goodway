/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;

/**
 * Sản phẩm đã đóng gói để giao hàng.
 * @author ThachLN
 */
public class Product implements Serializable {
    private Integer id;

    private String name;
    
    /** Thể tích đã đóng gói. length × width × height. Đơn vị mỗi cạnh là m.*/
    private double height;
    private double width;
    private double length;
    
    /** Trọng lượng đã đóng gói. Đơn vị tính là kg. */
    private double weight;

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
    * Get value of height.
    * @return the height
    */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value for height.
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
    * Get value of width.
    * @return the width
    */
    public double getWidth() {
        return width;
    }

    /**
     * Set the value for width.
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
    * Get value of length.
    * @return the length
    */
    public double getLength() {
        return length;
    }

    /**
     * Set the value for length.
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
    * Get value of weight.
    * @return the weight
    */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the value for weight.
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
