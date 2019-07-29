package xyz.frt.serveruser.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:39
 */
@FeignClient(name = "server-product")
public interface ServerProductClient {

    @GetMapping("/products/name/{name}")
    String getProductsByName(@PathVariable String name);

}
