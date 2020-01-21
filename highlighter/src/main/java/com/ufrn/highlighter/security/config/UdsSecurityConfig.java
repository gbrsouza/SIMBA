package com.ufrn.highlighter.security.config;

import com.ufrn.highlighter.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UdsSecurityConfig {

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder builder,
                                 PasswordEncoder passwordEncoder,
                                 UserDetailsServiceImpl userDetailsService) throws Exception
    {
        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

}
