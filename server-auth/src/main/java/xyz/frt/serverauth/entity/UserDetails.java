package xyz.frt.serverauth.entity;

import xyz.frt.servercommon.entity.User;

import java.security.Principal;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 上午1:55
 */
public class UserDetails implements Principal {

    private String name;

    private User user;

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
