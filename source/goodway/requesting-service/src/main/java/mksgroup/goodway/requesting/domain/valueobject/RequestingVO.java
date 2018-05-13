/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.domain.valueobject;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author ThachLN
 */
public class RequestingVO {

    private String name;
    private String id;
    private String orderId;
    private String userId;
    private LocalDate date;

    private LocalTime time;
    private String productId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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
     * Default Constructor
     */
    public RequestingVO() {
    }

    /**
     * Custom Constructor
     * @param name
     * @param id
     * @param orderId
     * @param userId
     * @param time
     * @param date
     */
    public RequestingVO(String id, String name, String orderId, String tableId, String userId, LocalDate date,
            LocalTime time) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
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
        return String.format("{id: %s, name: %s, userId: %s, orderId: %s" + ", tableId: %s, date: %s, time: %s}",
                id, name, userId, orderId, productId, date, time);
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
