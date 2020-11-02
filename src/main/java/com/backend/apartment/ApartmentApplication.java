package com.backend.apartment;

import com.skeleton.config.CROSWebConfig;
import com.skeleton.dto.AuthenticationResponse;
import com.skeleton.dto.LoginDto;
import com.skeleton.dto.UserDto;
import com.skeleton.response.SingleResult;
import com.skeleton.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = "com.skeleton")
@ComponentScan(basePackages = "com.backend.apartment")
@CrossOrigin(origins = ("http://localhost:4200"))
public class ApartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentApplication.class, args);
    }

    @Bean(name = "cross")
    public FilterRegistrationBean<CROSWebConfig> corsFilterRegistration() {
        FilterRegistrationBean<CROSWebConfig> registrationBean =
                new FilterRegistrationBean<>(new CROSWebConfig());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }



}
