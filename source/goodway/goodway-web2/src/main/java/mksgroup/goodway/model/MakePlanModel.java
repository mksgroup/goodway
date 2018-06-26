/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.model;

import java.util.List;

/**
 * @author ThachLN
 */
public class MakePlanModel {
    private List<Integer> orderIds;
    private List<Integer> vehicleIds;

    /**
     * Get value of orderIds.
     * @return the orderIds
     */
    public List<Integer> getOrderIds() {
        return orderIds;
    }
    /**
     * Get value of vehicleIds.
     * @return the vehicleIds
     */
    public List<Integer> getVehicleIds() {
        return vehicleIds;
    }
    /**
     * Set the value for orderIds.
     * @param orderIds the orderIds to set
     */
    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
    /**
     * Set the value for vehicleIds.
     * @param vehicleIds the vehicleIds to set
     */
    public void setVehicleIds(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

}
