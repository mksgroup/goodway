/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.app.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import mksgroup.goodway.app.domain.repository.ProductRepository;
import mksgroup.goodway.entity.Product;

/**
 * @author ThachLN
 */
@Controller
@SessionAttributes({"map_key"})
public class DeliveryController {
    /** For logging. */
    private static final Logger LOG = LoggerFactory.getLogger(DeliveryController.class);

    @Value("${map.key}")
    String mapKey;

    @Autowired
    ProductRepository productRepository;
    
    @GetMapping("/demo-order-delivery")
    String viewMaoTutor(ModelMap model) {
        model.addAttribute("map_key", mapKey);
        return "demo-order-delivery";
    }
    
    /**
     * Load danh sách các kiện hàng.
     * @param packageId
     * @return
     */
    @GetMapping("/load-package")
    @ResponseBody
    String loadPackage(@RequestParam("id") Integer packageId) {
        JsonObject jsonObject = new JsonObject();
        List<Product> products = productRepository.findProductByPackage(packageId);
        
        jsonObject.addProperty("data", new Gson().toJson(products));

        String json = jsonObject.toString();
        
        LOG.info("Json of the package:" + json);

        return json;

    }
}
