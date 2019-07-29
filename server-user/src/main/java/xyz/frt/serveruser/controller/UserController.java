package xyz.frt.serveruser.controller;

import org.springframework.web.bind.annotation.*;
import xyz.frt.serveruser.entity.User;
import xyz.frt.serveruser.entity.UserLoginDTO;
import xyz.frt.serveruser.service.UserService;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:49
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/products/name/{name}")
    public String getProductsByName(@PathVariable String name) {
        return userService.getProductsByName(name);
    }

    @PostMapping("/users/registry")
    public User postUser(@RequestBody User user) {
        return userService.registry(user);
    }

    @PostMapping("/users/login")
    public UserLoginDTO loginUser(@RequestHeader("Authorization") String authorize,
                                  @RequestParam("grant_type") String grantType,
                                  User user) {
        return userService.login(authorize, grantType, user);
    }


}

