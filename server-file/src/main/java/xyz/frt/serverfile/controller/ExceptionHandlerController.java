package xyz.frt.serverfile.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.frt.servercommon.common.JsonResult;
import xyz.frt.servercommon.common.ResultConst;
import xyz.frt.serverfile.excepiton.FileSystemException;

/**
 * @author phw 937855602@qq.com
 * create on 2019/9/23 下午4:38
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(FileSystemException.class)
    public JsonResult notFoundExceptionHandler(FileSystemException e) {
        return JsonResult.error(ResultConst.CODE_SERVER_ERR, e.getMessage(), e.getCause());
    }

}
