package xyz.frt.serveruser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.frt.serveruser.entity.JWT;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 下午2:48
 */
@FeignClient(name = "server-auth")
public interface ServerAuthClient {

    @PostMapping("/oauth/token")
    JWT getToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String grant_type,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);

}
