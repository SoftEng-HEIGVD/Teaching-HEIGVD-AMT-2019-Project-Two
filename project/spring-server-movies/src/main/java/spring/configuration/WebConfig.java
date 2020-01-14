package spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.api.endpoints.interceptors.JwtInterceptor;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    JwtInterceptor getJwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJwtInterceptor());
    }
}
