/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import mksgroup.goodway.support.HttpHandshakeInterceptor;

/**
 * @author ThachLN
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration addEndpoint = registry.addEndpoint("/gs-guide-websocket");
        addEndpoint.withSockJS();
        addEndpoint.addInterceptors(new HttpHandshakeInterceptor());
        
        // Allow new user to join
        registry.addEndpoint("/join-websocket").withSockJS();
    }

}