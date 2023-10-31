/*
package org.flower.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

@Override
    protected boolean sameOriginDisabled() {
        // 웹소켓에 CSRF를 비활성화.
        // 필요에 따라 변경 가능
        return true;
    }


    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        // 웹소켓 메시지에 대한 보안 정책을 정의
        messages
                .simpDestMatchers("/user/**").authenticated() // "/user/**" 주소를 가진 메시지는 인증된 사용자만 허용
                .anyMessage().permitAll(); // 나머지 메시지는 모든 사용자에게 허용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
*/
