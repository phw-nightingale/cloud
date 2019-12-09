package xyz.frt.serverauth.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.frt.servercommon.common.JsonResult;
import xyz.frt.servercommon.excepiton.UserLoginException;

/**
 * @author phw 937855602@qq.com
 * create on 2019/10/21 下午5:41
 */
@ControllerAdvice
public class AuthServerControllerAdviceConfiguration {

    @ResponseBody
    @ExceptionHandler(UserLoginException.class)
    public JsonResult exceptionHandler(Exception e) {
        e.printStackTrace();
        return JsonResult.error(400, e.getMessage());
    }

}
