package mksgroup.goodway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryPlanController {

	
	@RequestMapping(value = {"/deliveryplan","/deliveryplan/deliveryplan-index"})
	public String goDeliveryPlanIndex() {
		return "deliveryplan/deliveryplan-index";
	}
	
	@RequestMapping(value = {"/listcar","/deliveryplan/delivery-listcar"})
	public String goDeliveryPlanListCar() {
		return "deliveryplan/delivery-listcar";
	}
	
	@RequestMapping(value = {"/findway","/deliveryplan/delivery-result"})
	public String goDeliveryPlanResult() {
		return "deliveryplan/delivery-result";
	}
}
