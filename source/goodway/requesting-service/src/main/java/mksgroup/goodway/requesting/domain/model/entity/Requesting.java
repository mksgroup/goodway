/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.domain.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author ThachLN
 */
public class Requesting extends BaseEntity<String> {

    private String orderId;
    private String userId;
    private LocalDate date;
    private LocalTime time;
    /**
    * Get value of orderId.
    * @return the orderId
    */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Set the value for orderId.
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
    * Get value of productId.
    * @return the productId
    */
    public String getProductId() {
        return productId;
    }

    /**
     * Set the value for productId.
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String productId;


    /**
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * @param name
     * @param id
     * @param orderId
     * @param userId
     * @param time
     * @param date
     */
    public Requesting(String id, String name, String orderId, String productId, String userId, LocalDate date,
            LocalTime time) {
        super(id, name);
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }

    /**
     * Overridden toString() method that return String presentation of the Object
     * @return
     */
    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, userId: %s, vehicleId: %s" + ", tableId: %s, date: %s, time: %s}",
                id, name, userId, orderId, productId, date, time);
    }
}
