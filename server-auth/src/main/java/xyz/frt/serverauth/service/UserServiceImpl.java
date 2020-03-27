package xyz.frt.serverauth.service;

import org.apache.commons.io.FileUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import xyz.frt.serverauth.dao.UserRepository;
import xyz.frt.serverauth.dto.UserLoginDTO;
import xyz.frt.serverauth.util.ApplicationContextProvider;
import xyz.frt.servercommon.common.BPwdEncoderUtil;
import xyz.frt.servercommon.entity.JWT;
import xyz.frt.servercommon.entity.User;
import xyz.frt.servercommon.excepiton.UserLoginException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        cu.setToken(jwt.getAccess_token());
        userRepository.save(cu);

        ApplicationContextProvider.setCurrentUser(cu);

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

        // 获取basic.json基本配置文件
        String basicJson = "";
        try {
            File basic = ResourceUtils.getFile("classpath:template/basic.json");
            basicJson = FileUtils.readFileToString(basic);
        } catch (IOException e) {
            throw new UserLoginException("获取配置文件出错");
        }

        user.setSave(basicJson);
        user.setPassword(password);
        User temp = userRepository.findByUsername(user.getUsername());
        Assert.isNull(temp, "Username is already exists");
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getCurrent() {
        return ApplicationContextProvider.getCurrentUser();
    }

    @Override
    public void uploadConfig(String save) {
        User user  = ApplicationContextProvider.getCurrentUser();
        user.setSave(save);
        userRepository.save(user);
    }
}
