package com.polina.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

// Класс ответсвенный за конфигурацию Security
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Автоматическое подключение к БД подключение таблицы USERS  (username,password,enabled)
        auth.jdbcAuthentication().dataSource(dataSource);

//        UserBuilder userBuilder = User.withDefaultPasswordEncoder(); //временно
//        auth.inMemoryAuthentication() //сравнивает пароль из формы с паролем из метода
//                .withUser(userBuilder
//                        .username("polina")
//                        .password("polina")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder
//                        .username("ivan")
//                        .password("ivan")
//                        .roles("HR"))
//                .withUser(userBuilder.username("petr")
//                        .password("petr")
//                        .roles("HR", "MANAGER"));
    }


    // Разрешения для конкретного url конкретным ролям пользователя
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info/**").hasRole("MANAGER")// все адреса начинающиеся на /manager_info/
                .and().formLogin().permitAll();
    }
}
