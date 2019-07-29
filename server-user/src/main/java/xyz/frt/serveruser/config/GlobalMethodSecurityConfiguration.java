package xyz.frt.serveruser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author phw 937855602@qq.com
 * create on 19-7-29 下午2:36
 *
 * 用户在控制层对方法级别的访问控制
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfiguration {
}
