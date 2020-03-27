package xyz.frt.serverauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.servercommon.common.JsonResult;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 下午11:28
 */
@RestController
public class UnAuthorizationController {

    @RequestMapping("/401")
    public JsonResult _401() {
        return JsonResult.error(401, "用户凭证错误");
    }

    @RequestMapping("/403")
    public JsonResult _403() {
        return JsonResult.error(403, "用户凭证过期或无效");
    }

    @RequestMapping("/404")
    public JsonResult _404() {
        return JsonResult.error(404, "未知请求");
    }

}
