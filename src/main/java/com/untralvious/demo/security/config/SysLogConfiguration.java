package com.untralvious.demo.security.config;

import com.untralvious.demo.security.repository.SysLogRepository;
import com.untralvious.demo.security.service.LoggableDispatcherServlet;
import com.untralvious.demo.security.service.UserService;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class SysLogConfiguration {

    private final UserService userService;

    private final SysLogRepository sysLogRepository;

    public SysLogConfiguration(UserService userService, SysLogRepository sysLogRepository) {
        this.userService = userService;
        this.sysLogRepository = sysLogRepository;
    }

//    @Bean
//    public ServletRegistrationBean dispatcherRegistration() {
//        return new ServletRegistrationBean(dispatcherServlet());
//    }

//    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//    public DispatcherServlet dispatcherServlet() {
//        return new LoggableDispatcherServlet(userService, sysLogRepository);
//    }
}
