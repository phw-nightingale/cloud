package xyz.frt.serverfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author phw 937855602@qq.com
 * create on 2019/9/30 下午3:34
 */
@Configuration
public class ResourceHandlerConfiguration extends WebMvcConfigurationSupport {

    @Value("${spring.servlet.multipart.location}")
    private String basePath;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/asserts/**").addResourceLocations("file:" + basePath);
        super.addResourceHandlers(registry);
    }
}
