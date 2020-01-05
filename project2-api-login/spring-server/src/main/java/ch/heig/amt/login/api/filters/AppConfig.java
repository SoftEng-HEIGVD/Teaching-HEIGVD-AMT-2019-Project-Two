package ch.heig.amt.login.api.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean <isAdminFilter> filterRegistrationBean() {
        FilterRegistrationBean < isAdminFilter > registrationBean = new FilterRegistrationBean();
        isAdminFilter isAdminFilter = new isAdminFilter();

        registrationBean.setFilter(isAdminFilter);
        registrationBean.addUrlPatterns("/users");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean <isLoggedFilter> filterLoggedRegistrationBean() {
        FilterRegistrationBean < isLoggedFilter > registrationBean = new FilterRegistrationBean();
        isLoggedFilter isLoggedFilter = new isLoggedFilter();

        registrationBean.setFilter(isLoggedFilter);
        registrationBean.addUrlPatterns("/password");
        registrationBean.addUrlPatterns("/users");
        return registrationBean;
    }


}