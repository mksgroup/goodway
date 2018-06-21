/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.VehicleModel;
import rocky.common.CommonUtil;

/**
 * This utility provides features:<br/>
 * - Parse matrix of Vehicle from handsontable
 * @author ThachLN
 *
 */
public class AppUtil {

    public static Iterable<Vehicle> parseVehicle(@Valid VehicleModel data) {
        List<Vehicle> listVehicle = null;

        if (data == null) {
            return null;
        } else {
            List<List> rows = data.getData();
            if (rows != null) {
                listVehicle = new ArrayList<Vehicle>();
                
                Vehicle vehicle;
                Integer id;
                Double length;
                Double width;
                Double height;
                Double capacity;

                for (List rowItem : rows) {
                    vehicle = new Vehicle();
                    id = CommonUtil.isNNandNB(rowItem.get(0)) ?  (Integer) rowItem.get(0) : null;
                    length = CommonUtil.isNNandNB(rowItem.get(2))  ?  parseNum(rowItem.get(2)) : null;
                    width = CommonUtil.isNNandNB(rowItem.get(3))  ? parseNum(rowItem.get(3)) : null;
                    height = CommonUtil.isNNandNB(rowItem.get(4))  ? parseNum(rowItem.get(4)) : null;
                    capacity = CommonUtil.isNNandNB(rowItem.get(5))  ? parseNum(rowItem.get(5)) : null;

                    if (CommonUtil.isNNandNB(rowItem)) {
                        vehicle.setId(id);
                        vehicle.setName((String) rowItem.get(1));
                        vehicle.setLength(length);
                        vehicle.setWidth(width);
                        vehicle.setHeight(height);
                        vehicle.setCapacity(capacity);

                        vehicle.setCreated(new Date());
                        vehicle.setCreatedbyUsername("TBD");
                        
                        listVehicle.add(vehicle);
                    } else {
                        // Skip the end empty line
                    }
                }
            }
        }
        
        return listVehicle;
    }

    private static Double parseNum(Object obj) {
        Double num;

        if (obj == null) {
            return null;
        } else if (obj instanceof Integer) {
            num = 0.0 +  (Integer) obj;
        } else {
            num = Double.valueOf(obj.toString());
        }
        
        return num;
    }

}
