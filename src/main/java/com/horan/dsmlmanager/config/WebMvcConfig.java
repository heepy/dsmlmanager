package com.horan.dsmlmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private  BaseConfig baseConfig;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(securityInterceptor);
//        // 排除配置
//        addInterceptor.excludePathPatterns("/error");
//        addInterceptor.excludePathPatterns("/static/**");//排除静态资源
//        addInterceptor.excludePathPatterns("/view/login");
//        addInterceptor.excludePathPatterns("/login/check");
//        // 拦截配置
//        addInterceptor.addPathPatterns("/**");
//    }
//    @Override

    /**
     * 定位项目首页
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        WebMvcConfigurer.super.addViewControllers(registry);

    }

   /**
     * 访问静态资源
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/visit/**").addResourceLocations(baseConfig.getPath());
    }

    /**
     * 添加跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
    }
}
