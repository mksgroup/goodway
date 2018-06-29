package mksgroup.goodway.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.model.MakePlanModel;
import mksgroup.goodway.repository.DeliveryBatchRepository;
import mksgroup.goodway.repository.OrderRepository;
import mksgroup.goodway.repository.ProductRepository;

@Controller
public class PlanController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private DeliveryBatchRepository deliveryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@RequestMapping(value = {"/plan","/plan/wizard"})
	public String goDeliveryPlanSreach() {
		return "plan/wizard";
	}
	
//    /**
//     * Lập kế hoạch giao hàng.
//     * @param model chứa thông tin đầu vào: danh sách mã đơn hàng, danh sách mã đội xe.
//     * @return danh sách các đợt giao hàng.
//     */
//    @GetMapping(value = {"/plan/makePlan"})
//    public MakePlanModel  makeDeliveryPlanSreach(@Valid @RequestBody MakePlanModel data, Errors errors, HttpServletRequest request) {
//    	
//    	return data;
//    }
	
	@GetMapping("/plan/submit")
	public String submit() {
		return "/goodway";
	}
    
    @GetMapping("/plan/load-orderCustomer")
    @ResponseBody
    public List<Object[]> loadCustomer(){
    	List<Object[]> orders = orderRepository.findOrderCustomer();
    	
    	return orders;
    }
	
}
