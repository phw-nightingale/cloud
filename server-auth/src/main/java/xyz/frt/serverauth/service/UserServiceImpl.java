package xyz.frt.serverauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.frt.serverauth.dao.UserRepository;
import xyz.frt.serverauth.dto.UserLoginDTO;
import xyz.frt.servercommon.common.BPwdEncoderUtil;
import xyz.frt.servercommon.entity.JWT;
import xyz.frt.servercommon.entity.User;
import xyz.frt.servercommon.excepiton.UserLoginException;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 上午9:37
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ServerAuthClient serverAuthClient;

    public UserServiceImpl(UserRepository userRepository, ServerAuthClient serverAuthClient) {
        this.userRepository = userRepository;
        this.serverAuthClient = serverAuthClient;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
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
        Assert.notNull(user.getUsername(), "Username cannot be null");
        Assert.notNull(user.getPassword(), "Password cannot be null");
        String password = BPwdEncoderUtil.BCryptPassword(user.getPassword());
        user.setPassword(password);
        User temp = userRepository.findByUsername(user.getUsername());
        Assert.isNull(temp, "Username is already exists");
        return userRepository.saveAndFlush(user);
    }

}
