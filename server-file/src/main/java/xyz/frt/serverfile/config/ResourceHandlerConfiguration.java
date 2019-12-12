package xyz.frt.serverfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 此处必须实现WebMvcConfigurer
 * 否则全局时间格式化会失效
 *
 * @author phw 937855602@qq.com
 * create on 2019/9/30 下午3:34
 */
@Configuration
public class ResourceHandlerConfiguration implements WebMvcConfigurer {

    @Value("${spring.servlet.multipart.location}")
    private String basePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/asserts/**").addResourceLocations("file:" + basePath);
    }

}
