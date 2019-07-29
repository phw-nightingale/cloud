package xyz.frt.serveruser.service;


import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.frt.serveruser.entity.JWT;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 下午2:50
 */
@Slf4j
@Component
public class ServerAuthClientFallbackFactory implements FallbackFactory<ServerAuthClient> {
    @Override
    public ServerAuthClient create(Throwable throwable) {
        log.error(throwable.getClass().getName() + ":" + throwable.getMessage());
        return new ServerAuthClient() {
            @Override
            public JWT getToken(String authorization, String grant_type, String username, String password) {
                return null;
            }
        };
    }
}
