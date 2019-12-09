package xyz.frt.servercommon.excepiton;

public class UserLoginException extends RuntimeException {

    public UserLoginException(String errMsg) {
        super(errMsg);
    }

}
