package xyz.frt.serverauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.frt.serverauth.entity.User;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-26 上午9:45
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String s);

}
