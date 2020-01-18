package ch.heigvd.user.api.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MainInterceptor implements WebMvcConfigurer {

    @Autowired
    CheckAdminInterceptor checkAdminInterceptor;

    @Autowired
    CheckLogin checkLogin;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLogin).addPathPatterns("/user");
        registry.addInterceptor(checkAdminInterceptor).addPathPatterns("/user");
    }
}
