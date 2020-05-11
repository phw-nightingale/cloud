package xyz.frt.serverauth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.frt.serverauth.dao.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/27 下午11:17
 */
@Slf4j
public class CurrentUserInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    public CurrentUserInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        log.info("{}:{}", method, url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*String token = request.getHeader("Authorization");
        if (token == null) {
            token = request.getParameter("Authorization");
        }
        if (token != null) {
            User user = userRepository.findByToken(token);
            if (user != null) {
                ApplicationContextProvider.setCurrentUser(user);
            }
        }*/
    }
}
