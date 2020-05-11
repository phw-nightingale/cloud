package xyz.frt.serverauth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 上午1:55
 */
public class CurrentUser extends User {

    private String avatar;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
