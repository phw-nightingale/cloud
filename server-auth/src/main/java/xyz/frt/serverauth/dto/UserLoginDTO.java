package xyz.frt.serverauth.dto;

import xyz.frt.servercommon.entity.JWT;
import xyz.frt.servercommon.entity.User;

public class UserLoginDTO {

    private User user;

    private JWT jwt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }
}
