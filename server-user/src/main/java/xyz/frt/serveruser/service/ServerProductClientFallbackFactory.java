package xyz.frt.serveruser.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:45
 */
@Slf4j
@Component
public class ServerProductClientFallbackFactory implements FallbackFactory<ServerProductClient> {


    @Override
    public ServerProductClient create(Throwable throwable) {
        log.error(throwable.getMessage());
        return null;
    }

}
