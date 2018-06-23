package mksgroup.goodway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mksgroup.goodway.repository.DeliveryBatchRepository;
import mksgroup.goodway.repository.ProductRepository;

@Controller
public class PlanController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private DeliveryBatchRepository deliveryRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@RequestMapping(value = {"/plan/wizard"})
	public String goDeliveryPlanSreach() {
		return "plan/wizard";
	}
	
}
