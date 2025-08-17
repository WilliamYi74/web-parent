/**
 * @projectName web-parent
 * @package com.william.config
 * @className com.william.config.WebConfigure
 */
package com.william.config;

import com.william.utils.LogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * WebConfigure
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/16 16:03
 */
@Slf4j
@Configuration
public class WebConfigure extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }
}