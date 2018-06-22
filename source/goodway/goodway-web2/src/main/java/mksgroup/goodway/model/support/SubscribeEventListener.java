/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.model.support;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author ThachLN
 */
public class SubscribeEventListener implements ApplicationListener {
    private final static Logger LOG = LoggerFactory.getLogger(SubscribeEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent sessionSubscribeEvent) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
//        System.out.println(headerAccessor.getSessionAttributes().get("sessionId").toString());
        LOG.info("sessionSubscribeEvent=" + sessionSubscribeEvent);
        
        LOG.info("sessionSubscribeEvent.getSource()=" + sessionSubscribeEvent.getSource());
        
        
    }

}
