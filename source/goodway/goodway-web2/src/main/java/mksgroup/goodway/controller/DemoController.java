/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class DemoController {

    @Value("${map.key}")
    String mapKey;

    @RequestMapping("/demo")
    public String demo(ModelMap model) {
        model.addAttribute("map_key", mapKey);

        return "demo";
    }

}
