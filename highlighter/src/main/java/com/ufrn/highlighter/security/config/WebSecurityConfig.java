package com.ufrn.highlighter.security.config;

import com.ufrn.highlighter.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
                .permitAll()
            .and()
            .rememberMe()
            .userDetailsService(userDetailsService);

    }
}
