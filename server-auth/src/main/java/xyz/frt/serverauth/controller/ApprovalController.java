package xyz.frt.serverauth.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/4/29 下午12:12
 */
@Controller
@SessionAttributes("authorizationRequest")
public class ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {

        if (request.getAttribute("_csrf") != null) {
            model.put("_csrf", request.getAttribute("_csrf"));
        }
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)model.get("authorizationRequest");
        ModelAndView mv = new ModelAndView("/oauth2/base-grant" ,model);
        if (authorizationRequest != null) {
            mv.addObject("clientId", authorizationRequest.getClientId());
        } else {
            mv.setViewName("/oauth2/base-login");
            mv.addObject("error", "获取授权信息失败");
        }

        return mv;
    }
}
