package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mksgroup.goodway.entity.DeliveryBatch;
import mksgroup.goodway.entity.OrderMaster;
import mksgroup.goodway.model.MakePlanModel;
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
	
    /**
     * Lập kế hoạch giao hàng.
     * @param model chứa thông tin đầu vào: danh sách mã đơn hàng, danh sách mã đội xe.
     * @return danh sách các đợt giao hàng.
     */
    @RequestMapping(value = {"/plan/makePlan"})
    public Iterable<DeliveryBatch> makeDeliveryPlanSreach(MakePlanModel model) {
        List<DeliveryBatch> resultBatchs = new ArrayList<DeliveryBatch>();
        
        // Demo data
        DeliveryBatch batch = new DeliveryBatch(1, "Chuyến 1", 1, new Date(), "ThachLN");
        OrderMaster order = new OrderMaster(1, "DH1", 1, new Date(), new Date(), "ThachLN");
        batch.setOrderId(order);
        
        resultBatchs.add(batch);
        
        return resultBatchs;
    }
	
}
