package mksgroup.goodway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mksgroup.goodway.entity.Customer;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.repository.CustomerRepository;
import mksgroup.goodway.repository.DeliveryBatchRepository;
import mksgroup.goodway.repository.OrderRepository;
import mksgroup.goodway.repository.ProductRepository;

@Controller
public class PlanController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
	@Autowired
	private DeliveryBatchRepository deliveryRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@RequestMapping(value = {"/plan/wizard"})
	public String goDeliveryPlanSreach() {
		return "plan/wizard";
	}
	
//    /**
//     * Lập kế hoạch giao hàng.
//     * @param model chứa thông tin đầu vào: danh sách mã đơn hàng, danh sách mã đội xe.
//     * @return danh sách các đợt giao hàng.
//     */
//    @GetMapping("/plan/load-product")
//    public Iterable<DeliveryBatch> makeDeliveryPlanSreach() {
//    	System.out.println("abc");
//        LOG.info("plan/makePlan");
//     
//        List<DeliveryBatch> resultBatchs = new ArrayList<DeliveryBatch>();
//        
//        // Demo data
//        DeliveryBatch batch = new DeliveryBatch(1, "SonCT", 1, new Date(), "SonCT");
//        OrderMaster order = new OrderMaster(1, "SonCT", 1, new Date(), new Date(), "SonCT");
//        
//        batch.setOrderId(order);
//        
//        resultBatchs.add(batch);
//        
//        return resultBatchs;
//    }
    
//    @GetMapping("/order/load-byCustomer")
//    @ResponseBody
//    public OrderMaster loadOrder(@RequestParam("customerId") Integer customerId) {
//    	
//    	Customer customer = customerRepository.findById(customerId).get();
//        OrderMaster order = orderRepository.findByCustomerId(customer);
//        
//        return order;
//    }
	
}
