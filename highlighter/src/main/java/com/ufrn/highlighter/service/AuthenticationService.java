package com.ufrn.highlighter.service;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {

    private final ApplicationUserRepository applicationUserRepository;
    public boolean isRegister (ApplicationUser user){
        ApplicationUser userTest = applicationUserRepository.findByUsername(user.getUsername());
        if (userTest == null || !userTest.getPassword().equals(user.getPassword())) return false;
        return true;
    }

}
