package com.polina.spring.security.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

// Класс ответсвенный за конфигурацию Security
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder(); //временно
        auth.inMemoryAuthentication() //сравнивает пароль из формы с паролем из метода
                .withUser(userBuilder
                        .username("polina")
                        .password("polina")
                        .roles("EMPLOYEE"))
                .withUser(userBuilder
                        .username("ivan")
                        .password("ivan")
                        .roles("HR"))
                .withUser(userBuilder.username("petr")
                        .password("petr")
                        .roles("HR","MANAGER"));
    }
}
