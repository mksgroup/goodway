package mksgroup.goodway.plan.domain.repository;

import java.util.Collection;

import mksgroup.goodway.plan.domain.model.entity.Vehicle;

/**
 *
 * @author ThachLN
 * @param <Vehicle>
 * @param <String>
 */
public interface VehicleRepository<Restaurant, String> extends Repository<Vehicle, String> {

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
    public Collection<Vehicle> findByName(String name) throws Exception;
}
