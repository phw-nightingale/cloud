package xyz.frt.serveruser.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.frt.serveruser.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

}
