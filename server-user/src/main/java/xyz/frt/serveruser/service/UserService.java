package xyz.frt.serveruser.service;

import xyz.frt.serveruser.entity.User;
import xyz.frt.serveruser.entity.UserLoginDTO;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:36
 */
public interface UserService {


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

    String getProductsByName(String name);

}
