package xyz.frt.serverauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.frt.serverauth.dto.UserLoginDTO;
import xyz.frt.servercommon.entity.User;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:36
 */
public interface UserService extends UserDetailsService {


    /**
     * 登录
     * @param grantType
     * @param authorize
     * @param user
     * @return
     */
    UserLoginDTO login(String grantType, String authorize, User user);

    /**
     * 注册
     * @param user 注册信息
     * @return 当前用户
     */
    User registry(User user);

    User getCurrent();

    void uploadConfig(String save);
}
