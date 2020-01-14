package com.ufrn.highlighter.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/bootstrap/**", "/docs/**", "/img/**", "/vendor/**").permitAll()
                .antMatchers("/account").permitAll()
                .antMatchers("/welcome").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();

    }
}
