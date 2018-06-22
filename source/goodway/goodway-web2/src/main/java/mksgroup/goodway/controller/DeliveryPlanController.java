package mksgroup.goodway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import mksgroup.goodway.entity.DeliveryBatch;
import mksgroup.goodway.entity.Product;
import mksgroup.goodway.repository.DeliveryBatchRepository;
import mksgroup.goodway.repository.ProductRepository;

@Controller
public class DeliveryPlanController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private DeliveryBatchRepository deliveryRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@RequestMapping(value = {"/deliveryplan","/deliveryplan/delivery-sreach"})
	public String goDeliveryPlanSreach() {
		return "deliveryplan/delivery-sreach";
	}
	
	@RequestMapping(value = {"/deliveryplan/listcar","/deliveryplan/delivery-listcar"})
	public String goDeliveryPlanListCar() {
		return "deliveryplan/delivery-listcar";
	}
	
	@RequestMapping(value = {"/deliveryplan/findway","/deliveryplan/delivery-result"})
	public String goDeliveryPlanResult() {
		return "deliveryplan/delivery-result";
	}
	
	@GetMapping("/delivery/load-deliverylist")
	@ResponseBody
	public Iterable<DeliveryBatch> loadDelivery(){
		Iterable<DeliveryBatch> deliveryList = deliveryRepository.findAll();
		return deliveryList;
	}
	
    /**
     * Load danh sách các kiện hàng.
     * @param packageId
     * @return
     */
    @GetMapping("/load-package")
    @ResponseBody
    Iterable<Product> loadPackage(@RequestParam("id") Integer packageId) {

        Iterable<Product> products = productRepository.findAll();

        return products;

    }
}
