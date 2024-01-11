package com.example.messagingstompwebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * @EnableWebSocketMessageBroker 어노테이션은 메시지 브로커에에 의해 지원되는 웹소캣 메시지 처리를 활성화한다.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        /*
        * enableSimpleBroker() : enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with /topic
        * */
        config.enableSimpleBroker("/topic");

        /*
        * designates the /app prefix for messages that are bound for methods annotated with @MessageMapping
        * This prefix will be used to define all the message mappings.
        * For example, /app/hello is the endpoint that the GreetingController.greeting() method is mapped to handle.
        * */
        config.setApplicationDestinationPrefixes("/app");
    }

    /*
    * registers the /gs-guide-websocket endpoint for websocket connections
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket");
    }


}
