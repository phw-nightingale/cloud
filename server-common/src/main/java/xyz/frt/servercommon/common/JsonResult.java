package xyz.frt.servercommon.common;

import java.io.Serializable;


/**
 * @author phw 937855602@qq.com
 * create on 2019/9/19 下午4:43
 */
public class JsonResult implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    private JsonResult() { }

    private JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JsonResult success() {
        return new JsonResult(ResultConst.CODE_SUCCESS, ResultConst.MSG_SUCCESS);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(ResultConst.CODE_SUCCESS, ResultConst.MSG_SUCCESS, data);
    }

    public static JsonResult success(Integer code, String msg) {
        return new JsonResult(code, msg);
    }

    public static JsonResult success(Integer code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult error(Integer code, String msg, Object details) {
        return new JsonResult(code, msg, details);
    }

    public static JsonResult error(Integer code, String msg) {
        return new JsonResult(code, msg);
    }

    public static JsonResult error(Integer code) {
        return new JsonResult(code, ResultConst.MSG_FAIL);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
