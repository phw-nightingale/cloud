package xyz.frt.serverauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.frt.servercommon.entity.User;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午9:45
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

}
