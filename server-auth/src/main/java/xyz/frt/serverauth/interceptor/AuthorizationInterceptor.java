package xyz.frt.serverauth.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import xyz.frt.serverauth.dao.UserRepository;
import xyz.frt.servercommon.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 下午11:17
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private UserRepository userRepository;

    public AuthorizationInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.substring(token.indexOf(" ") + 1);
            User user = userRepository.findByToken(token);
            if (user != null) {
                request.setAttribute("user", user);
                return true;
            } else {
                response.sendRedirect("/403");
            }
        } else {
            response.sendRedirect("/401");
        }
        return false;
    }

}
