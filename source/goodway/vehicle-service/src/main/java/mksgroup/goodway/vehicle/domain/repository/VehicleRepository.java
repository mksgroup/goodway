package mksgroup.goodway.vehicle.domain.repository;

import java.util.Collection;

import mksgroup.goodway.vehicle.domain.model.entity.Vehicle;

/**
 *
 * @author ThachLN
 * @param <Vehicle>
 * @param <String>
 */
public interface VehicleRepository<Vehicle, String> extends Repository<Vehicle, String> {

    /**
     *
     * @param name
     * @return
     */
    boolean containsName(String name);

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    Collection<Vehicle> findByName(String name) throws Exception;
}
