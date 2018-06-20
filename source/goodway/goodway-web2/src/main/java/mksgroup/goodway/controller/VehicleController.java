/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.repository.VehicleRepository;

/**
 * This controller processes requests about Vehicle.
 * @author ThachLN
 */
@Controller
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;
    
    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping({"/vehicle", "/vehicle/search"})
    public String goVehicleSearch() {
        return "vehicle/search";
    }

    @RequestMapping("/vehicle/new")
    public String goVehiclerNew() {
        return "vehicle/new";
    }
    
    
    /**
     * Load danh sách các xe.
     * @param packageId
     * @return
     */
    @GetMapping("/order/load-vehicle")
    @ResponseBody
    public Iterable<Vehicle> loadVehicles() {

        Iterable<Vehicle> orders = vehicleRepository.findAll();
        
        return orders;
    }
}
