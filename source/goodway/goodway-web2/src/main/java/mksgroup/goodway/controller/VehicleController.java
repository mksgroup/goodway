/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.biz.VehicleBiz;
import mksgroup.goodway.entity.Vehicle;
import mksgroup.goodway.model.VehicleModel;
import mksgroup.goodway.util.AppUtil;

/**
 * This controller processes requests about Vehicle.
 * @author ThachLN
 */
@Controller
public class VehicleController extends BaseController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(VehicleController.class);
    
    @Autowired
    private VehicleBiz vehicleBiz;
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
    @GetMapping("/vehicle/load-vehicle")
    @ResponseBody
    public Iterable<Vehicle> loadVehicles() {

        Iterable<Vehicle> vehicles = vehicleBiz.getRepo().findAll();
        
        return vehicles;
    }
    
    
    @PostMapping("/vehicle/save")
    @ResponseBody
    public Iterable<Vehicle> saveVehicles(@Valid @RequestBody VehicleModel data, Errors errors, HttpServletRequest request) {
        LOG.info("saveVehicles....");
        
        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return null;
        } else {
            Iterable<Vehicle> entities = AppUtil.parseVehicle(data);
            vehicleBiz.updateVehicles(entities, data.getDeletedIds());
//            List<Vehicle> entityList = new ArrayList<Vehicle>();
//
//            if (data.getDeletedIds() != null) {
//                data.getDeletedIds().forEach(deletedId -> {
//                    vehicleRepository.deleteById(deletedId);
//                });
//            }
//            
//            vehicleRepository.saveAll(entities);
//            LOG.info("vehicleModel=" + data + ";request=" + request);
        }
        
        // Reload data from db
        Iterable<Vehicle> orders = vehicleBiz.getRepo().findAll();
        
        return orders;
    }

    @GetMapping("/vehicle/export")
    @ResponseBody
    public void downloadVehicle(HttpServletResponse response) {
        LOG.debug("download vehicle....");
        
        try {
            downloadExcel(response);
        } catch (IOException ex) {
            LOG.error("Could not download vehicle.", ex);
        }
    }

    /**
     * Downloaded file name for vehicle.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getFilename()
     */
    @Override
    String getFilename() {
        return "Vehicle.xlsx";
    }

    /**
     * Template for downloading vehicles.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getTemplate()
     */
    @Override
    String getTemplate() {
        return "/excel-templates/Template_Vehicle.xlsx";
    }

    /**
     * Get all vehicle for downloading.
     * @return
     * @see mksgroup.goodway.controller.BaseController#getDownloadData()
     */
    @Override
    Iterable<?> getDownloadData() {
        return vehicleBiz.getRepo().findAll();
    }
}
