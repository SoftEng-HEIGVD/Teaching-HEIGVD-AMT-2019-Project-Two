package ch.heigvd.amt.users.api.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

public class ApplyConfigFilter {
    @Autowired
    isValidTokenFilter isValidTokenFilter;

    public FilterRegistrationBean tokenConfigFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(isValidTokenFilter);
        filterRegistrationBean.addUrlPatterns("/users");

        return filterRegistrationBean;
    }

}
