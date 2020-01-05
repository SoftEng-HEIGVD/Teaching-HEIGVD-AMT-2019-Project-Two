package spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.api.endpoints.interceptors.AdminInterceptor;
import spring.api.endpoints.interceptors.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .excludePathPatterns("/registrations/*", "/login/*");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/users/*");
    }
}
