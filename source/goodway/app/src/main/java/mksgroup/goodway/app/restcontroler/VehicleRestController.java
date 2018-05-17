package mksgroup.goodway.app.restcontroler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mksgroup.goodway.dao.IGenericDao;
import mksgroup.goodway.domain.valueobject.VehicleVO;
import mksgroup.goodway.entity.Vehicle;

@RestController
@RequestMapping("/v1/vehicle")
public class VehicleRestController {
    protected static final Logger LOG = Logger.getLogger(VehicleRestController.class.getName());
    
    protected IGenericDao<Vehicle> vehicleRepository;
    
    @Autowired
    public VehicleRestController(IGenericDao<Vehicle> vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Vehicle> add(@RequestBody VehicleVO vehicleVO) {
        LOG.info(String.format("VehicleRestController add() invoked: %s for %s", vehicleRepository.getClass().getName(), vehicleVO.getName()));
        LOG.info(vehicleVO.toString());
        Vehicle vehicleEntiry= new Vehicle();

        // Transfer data from VO to Entity
        BeanUtils.copyProperties(vehicleVO, vehicleEntiry);
        
        try {
            vehicleRepository.create(vehicleEntiry);
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Exception raised add Vehicle REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
