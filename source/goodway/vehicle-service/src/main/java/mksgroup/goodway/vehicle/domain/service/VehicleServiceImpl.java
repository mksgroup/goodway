package mksgroup.goodway.vehicle.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mksgroup.goodway.vehicle.domain.model.entity.Entity;
import mksgroup.goodway.vehicle.domain.model.entity.Vehicle;
import mksgroup.goodway.vehicle.domain.repository.VehicleRepository;

/**
 *
 * @author ThachLN
 */
@Service("vehicleService")
public class VehicleServiceImpl extends BaseService<Vehicle, String>
        implements VehicleService {

    private VehicleRepository<Vehicle, String> vehicleRepository;

    /**
     *
     * @param vehicleRepository
     */
    @Autowired
    public VehicleServiceImpl(VehicleRepository<Vehicle, String> vehicleRepository) {
        super(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void add(Vehicle vehicle) throws Exception {
        if (vehicleRepository.containsName(vehicle.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", vehicle.getName()));
        }

        if (vehicle.getName() == null || "".equals(vehicle.getName())) {
            throw new Exception("Restaurant name cannot be null or empty string.");
        }
        super.add(vehicle);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Vehicle> findByName(String name) throws Exception {
        return vehicleRepository.findByName(name);
    }

    /**
     *
     * @param vehicle
     * @throws Exception
     */
    @Override
    public void update(Vehicle vehicle) throws Exception {
        vehicleRepository.update(vehicle);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(String id) throws Exception {
        vehicleRepository.remove(id);
    }

    /**
     *
     * @param vehicleId
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(String vehicleId) throws Exception {
        return vehicleRepository.get(vehicleId);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Vehicle> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
