package xyz.frt.serveruser.common;

public class UserLoginException extends RuntimeException {

    public UserLoginException(String errMsg) {
        super(errMsg);
    }

}
