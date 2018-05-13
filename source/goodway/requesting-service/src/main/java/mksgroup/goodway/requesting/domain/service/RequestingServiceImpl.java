package mksgroup.goodway.requesting.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mksgroup.goodway.requesting.domain.model.entity.Requesting;
import mksgroup.goodway.requesting.domain.model.entity.Entity;
import mksgroup.goodway.requesting.domain.repository.RequestingRepository;

/**
 *
 * @author ThachLN
 */
@Service("requestingService")
public class RequestingServiceImpl extends BaseService<Requesting, String>
        implements RequestingService {

    private RequestingRepository<Requesting, String> requestingRepository;

    /**
     *
     * @param requestingRepository
     */
    @Autowired
    public RequestingServiceImpl(RequestingRepository<Requesting, String> requestingRepository) {
        super(requestingRepository);
        this.requestingRepository = requestingRepository;
    }

    @Override
    public void add(Requesting requesting) throws Exception {
        if (requestingRepository.containsName(requesting.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", requesting.getName()));
        }

        if (requesting.getName() == null || "".equals(requesting.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(requesting);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Requesting> findByName(String name) throws Exception {
        return requestingRepository.findByName(name);
    }

    /**
     *
     * @param requesting
     * @throws Exception
     */
    @Override
    public void update(Requesting requesting) throws Exception {
        requestingRepository.update(requesting);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(String id) throws Exception {
        requestingRepository.remove(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Entity findById(String id) throws Exception {
        return requestingRepository.get(id);
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Collection<Requesting> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
