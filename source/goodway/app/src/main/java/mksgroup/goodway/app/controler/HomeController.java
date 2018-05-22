/**
 * Copyright (C) 2018 MKSGROUP - All Rights Reserved.
 */
package mksgroup.goodway.app.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ThachLN
 */
@Controller
public class HomeController {
    /** For logging. */
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Value("${map.key}")
    String mapKey;

    @GetMapping("/")
    String home(ModelMap model) {
        LOG.info("Processing /");
        model.addAttribute("map_key", mapKey);

        return "demo-order-delivery";
    }
}
