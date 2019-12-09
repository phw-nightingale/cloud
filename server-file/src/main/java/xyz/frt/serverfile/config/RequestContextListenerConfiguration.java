package xyz.frt.serverfile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author phw 937855602@qq.com
 * create on 2019/9/30 下午2:58
 */
@Configuration
public class RequestContextListenerConfiguration {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
