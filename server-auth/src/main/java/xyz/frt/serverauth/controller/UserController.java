package xyz.frt.serverauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午10:15
 */
@RestController
public class UserController {

    @GetMapping("/users/current")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

}
