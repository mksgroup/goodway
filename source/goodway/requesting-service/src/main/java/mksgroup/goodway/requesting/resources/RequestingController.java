/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.requesting.resources;

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

import mksgroup.goodway.requesting.domain.model.entity.Requesting;
import mksgroup.goodway.requesting.domain.model.entity.Entity;
import mksgroup.goodway.requesting.domain.service.RequestingService;
import mksgroup.goodway.requesting.domain.valueobject.RequestingVO;

/**
 *
 * @author ThachLN
 */
@RestController
@RequestMapping("/v1/requesting")
public class RequestingController {

    /**
     *
     */
    protected static final Logger logger = Logger.getLogger(RequestingController.class.getName());

    /**
     *
     */
    protected RequestingService requestingService;

    /**
     *
     * @param requestingService
     */
    @Autowired
    public RequestingController(RequestingService requestingService) {
        this.requestingService = requestingService;
    }

    /**
     * Fetch requests with the specified name. A partial case-insensitive match
     * is supported. So <code>http://.../requesting/rest</code> will find any
     * requests with upper or lower case 'rest' in their name.
     *
     * @param name
     * @return A non-null, non-empty collection of requests.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Requesting>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("requesting-service findByName() invoked:{} for {} ", requestingService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Requesting> requestings;
        try {
            requestings = requestingService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return requestings.size() > 0 ? new ResponseEntity<>(requestings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetch requests with the given id.
     * <code>http://.../v1/requestings/{id}</code> will return requesting with given
     * id.
     *
     * @param id
     * @return A non-null, non-empty collection of requesting.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("id") String id) {
        logger.info(String.format("requesting-service findById() invoked:{} for {} ", requestingService.getClass().getName(), id));
        id = id.trim();
        Entity request;
        try {
            request = requestingService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return request != null ? new ResponseEntity<>(request, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add request with the specified information.
     *
     * @param requestingVO
     * @return A non-null requesting.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Requesting> add(@RequestBody RequestingVO requestingVO) {
        logger.info(String.format("requesting-service add() invoked: %s for %s", requestingService.getClass().getName(), requestingVO.getName()));
        System.out.println(requestingVO);
        Requesting requesting = new Requesting(null, null, null, null, null, null, null);
        BeanUtils.copyProperties(requestingVO, requesting);
        try {
            requestingService.add(requesting);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Requesting REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
