package xyz.frt.serverauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.frt.serverauth.service.UserService;
import xyz.frt.servercommon.common.JsonResult;
import xyz.frt.servercommon.entity.User;

import java.security.Principal;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午10:15
 */
@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/current")
    public JsonResult getPrincipal(Principal principal) {
        return JsonResult.success(principal);
    }

    @GetMapping("/users/info")
    public JsonResult getCurrent(@RequestHeader("Authorization") String access_token) {
        if (access_token != null) {
            access_token = access_token.split(" ")[1];
        }
        return JsonResult.success(userService.getCurrent(access_token));
    }

    @PostMapping("/users/registry")
    public JsonResult postUser(User user) {
        return JsonResult.success(userService.registry(user));
    }

    @PostMapping("/users/login")
    public JsonResult loginUser(@RequestHeader("ClientDetails") String authorize,
                                @RequestParam("grant_type") String grantType,
                                User user) {
        return JsonResult.success(userService.login(authorize, grantType, user));
    }

    @PutMapping("/users/save")
    public JsonResult uploadConfig(@RequestHeader("Authorization") String token,  String save) {
        if (token != null) {
            token = token.split(" ")[1];
            userService.uploadConfig(token, save);
            return JsonResult.success();
        }
        return JsonResult.error(403, "token invalid");
    }

}
