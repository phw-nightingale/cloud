package xyz.frt.serverauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/4/23 下午7:09
 */
@Controller
public class CommonController {

    @GetMapping("/oauth2/login")
    public String loginPage() {
        System.out.print("/oauth2/login");
        return "/oauth2/base-login";
    }

}
