/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.view.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller processes request /
 * @author ThachLN
 */
@Controller
public class ProjecController {

    /**
     * Goto the index page.
     * @return
     */
    @RequestMapping("/summary")
    public String login() {
        return "project/summary";
    }
}
