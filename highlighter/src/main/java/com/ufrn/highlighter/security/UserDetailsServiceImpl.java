package com.ufrn.highlighter.security;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.repository.ApplicationUserRepository;
import com.ufrn.highlighter.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserService applicationUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserService.getUserByUsername(username);
        if(applicationUser == null)
            throw new UsernameNotFoundException(String.format("Application user '%s' not found", username));
        return new CustomUserDetails(applicationUser);
    }
}
