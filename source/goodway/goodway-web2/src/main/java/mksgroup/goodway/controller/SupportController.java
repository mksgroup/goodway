/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mksgroup.goodway.model.support.ChatMessage;

/**
 * @author ThachLN
 *
 */
@Controller
public class SupportController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(SupportController.class);
    
    private List<String> users = new ArrayList<>();

    @RequestMapping({"/support", "/support/online"})
    public String goSupportOnline() {
        return "support/online";
    }
    
    /**
     * Catch a new member join to the group chat.
     * @param name
     * @return
     */
    @SubscribeMapping("/greetings")
    public int chatInit() {
        LOG.info("chatInit...");
        
        String name = "noname";
        users.add(name);

        return users.size();
    }

    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(ChatMessage message) throws Exception {
//        LOG.info("greeting...");
//        String outMessage = String.format("%s: %s", message.getName(), message.getContent());
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        
        
        return message.getContent();
    }
    
    @MessageMapping("/list-user")
    @SendTo("/user/list")
    public String getUser(String user) throws Exception {        
        return user;
    }
}
