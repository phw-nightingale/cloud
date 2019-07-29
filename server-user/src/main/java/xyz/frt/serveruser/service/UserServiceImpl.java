package xyz.frt.serveruser.service;

import org.springframework.stereotype.Service;
import xyz.frt.serveruser.common.BPwdEncoderUtil;
import xyz.frt.serveruser.common.UserLoginException;
import xyz.frt.serveruser.dao.UserRepository;
import xyz.frt.serveruser.entity.JWT;
import xyz.frt.serveruser.entity.User;
import xyz.frt.serveruser.entity.UserLoginDTO;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:37
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ServerAuthClient serverAuthClient;

    private final ServerProductClient serverProductClient;

    public UserServiceImpl(ServerProductClient serverProductClient, UserRepository userRepository, ServerAuthClient serverAuthClient) {
        this.serverProductClient = serverProductClient;
        this.userRepository = userRepository;
        this.serverAuthClient = serverAuthClient;
    }

    @Override
    public UserLoginDTO login(String authorize, String grantType, User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
        throw new UserLoginException("用户名或密码不能为空");
    }
        User cu = userRepository.findByUsername(user.getUsername());
        if (cu == null) {
            throw new UserLoginException("用户不存在");
        }
        if (!BPwdEncoderUtil.matches(user.getPassword(), cu.getPassword())) {
            throw new UserLoginException("密码不正确");
        }
        JWT jwt = serverAuthClient.getToken(authorize, grantType, user.getUsername(), user.getPassword());
        if (jwt == null) {
            throw new UserLoginException("获取JWT错误");
        }
        UserLoginDTO dto = new UserLoginDTO();
        dto.setUser(cu);
        dto.setJwt(jwt);
        return dto;
    }

    @Override
    public User registry(User user) {
        return null;
    }

    @Override
    public String getProductsByName(String name) {
        return "Accessed by SERVER-USER: " + serverProductClient.getProductsByName(name);
    }


}
