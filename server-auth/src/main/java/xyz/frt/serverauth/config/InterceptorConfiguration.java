package xyz.frt.serverauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.frt.serverauth.dao.UserRepository;
import xyz.frt.serverauth.interceptor.AuthorizationInterceptor;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 下午11:39
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private UserRepository userRepository;

    public InterceptorConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(userRepository))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/oauth/**",
                        "/users/login",
                        "/users/registry",
                        "/401",
                        "/403",
                        "/404");
    }
}
