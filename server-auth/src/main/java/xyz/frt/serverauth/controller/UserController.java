package xyz.frt.serverauth.controller;

import org.springframework.web.bind.annotation.*;
import xyz.frt.serverauth.dto.UserLoginDTO;
import xyz.frt.serverauth.service.UserService;
import xyz.frt.servercommon.common.JsonResult;
import xyz.frt.servercommon.entity.User;

import java.security.Principal;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午10:15
 */
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

    @GetMapping("/users/token")
    public JsonResult getCurrent() {
        return JsonResult.success(userService.getCurrent());
    }

    @PostMapping("/users/registry")
    public JsonResult postUser(User user) {
        return JsonResult.success(userService.registry(user));
    }

    @PostMapping("/users/login")
    public JsonResult loginUser(@RequestHeader("Authorization") String authorize,
                                @RequestParam("grant_type") String grantType,
                                User user) {
        return JsonResult.success(userService.login(authorize, grantType, user));
    }

    @PutMapping("/users/save")
    public JsonResult uploadConfig(String save) {
        userService.uploadConfig(save);
        return JsonResult.success();
    }
}
