package mksgroup.goodway.plan.resources;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mksgroup.goodway.plan.domain.model.entity.Entity;
import mksgroup.goodway.plan.domain.model.entity.Vehicle;
import mksgroup.goodway.plan.domain.service.VehicleService;
import mksgroup.goodway.plan.domain.valueobject.VehicleVO;

/**
 *
 * @author ThachLN
 */
@RestController
@RequestMapping("/v1/plans")
public class VehicleController {

    /**
     *
     */
    protected static final Logger logger = Logger.getLogger(VehicleController.class.getName());

    /**
     *
     */
    protected VehicleService vehicleService;

    /**
     *
     * @param vehicleService
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Fetch vehicles with the specified name. A partial case-insensitive
     * match is supported. So <code>http://.../plans/rest</code> will find
     * any plans with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of plans.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Vehicle>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("plan-service findByName() invoked:{} for {} ", vehicleService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Vehicle> vehicles;
        try {
            vehicles = vehicleService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return vehicles.size() > 0 ? new ResponseEntity<>(vehicles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch vehicles with the given id.
     * <code>http://.../v1/vehicles/{vehicle_id}</code> will return
     * vehicle with given id.
     *
     * @param id
     * @return A non-null, non-empty collection of vehicles.
     */
    @RequestMapping(value = "/{vehicle_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("vehicle_id") String id) {
        logger.info(String.format("plan-service findById() invoked:{} for {} ", vehicleService.getClass().getName(), id));
        id = id.trim();
        Entity vehicle;
        try {
            vehicle = vehicleService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return vehicle != null ? new ResponseEntity<>(vehicle, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add vehicle with the specified information.
     *
     * @param vehicleVO
     * @return A non-null vehicle.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Vehicle> add(@RequestBody VehicleVO vehicleVO) {
        logger.info(String.format("plan-service add() invoked: %s for %s", vehicleService.getClass().getName(), vehicleVO.getName()));
        System.out.println(vehicleVO);
        Vehicle vehicle = new Vehicle(null, null);
        BeanUtils.copyProperties(vehicleVO, vehicle);
        try {
            vehicleService.add(vehicle);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Restaurant REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
