package mksgroup.goodway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryPlanController {

	
	@RequestMapping(value = {"/deliveryplan","/deliveryplan/deliveryplan"})
	public String deliveryPlan() {
		return "deliveryplan/deliveryplan";
	}
	
}
