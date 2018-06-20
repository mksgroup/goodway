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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author ThachLN
 */
@Controller
@SessionAttributes({"map_key"})
public class TutorController {
    /** For logging. */
    private static final Logger LOG = LoggerFactory.getLogger(TutorController.class);

    @Value("${map.key}")
    String mapKey;

    @GetMapping("/address")
    String address(ModelMap model) {
        model.addAttribute("map_key", mapKey);
        return "address";
    }
    
    @GetMapping("/marker-clustering")
    String markerClustering(ModelMap model) {
        model.addAttribute("map_key", mapKey);
        return "marker-clustering";
    }
    
    @GetMapping("/map-tutors-select-address")
    String viewMaoTutor(ModelMap model) {
        model.addAttribute("map_key", mapKey);
        return "map-tutors-select-address";
    }
}
