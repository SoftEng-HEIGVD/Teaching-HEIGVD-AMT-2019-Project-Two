package ch.heigvd.amt.users.api.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {

    @Bean
    public FilterRegistrationBean<isLoginFilter> filterLoginRegistrationBean(){
        FilterRegistrationBean<isLoginFilter> registrationBean = new FilterRegistrationBean<>();
        isLoginFilter isLoginFilter = new isLoginFilter();

        registrationBean.setFilter(isLoginFilter);
        registrationBean.addUrlPatterns("/users/*", "/users");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<isAdministratorFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<isAdministratorFilter> registrationBean = new FilterRegistrationBean<>();
        isAdministratorFilter isAdministratorFilter = new isAdministratorFilter();

        registrationBean.setFilter(isAdministratorFilter);
        registrationBean.addUrlPatterns("/users");
        return registrationBean;
    }


}
