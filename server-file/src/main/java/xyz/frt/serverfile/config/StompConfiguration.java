package xyz.frt.serverfile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author phw 937855602@qq.com
 * create on 2019/10/15 下午2:43
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的websocket端点
     * @param registry 注册器
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/stomp").setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 配置消息代理
     * @param registry 注册器
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //订阅主题前缀
        registry.enableSimpleBroker("/queue", "/topic");
        //全局消息前缀
        registry.setApplicationDestinationPrefixes("/app");
        //点对点推送前缀
        registry.setUserDestinationPrefix("/user");
    }
}
