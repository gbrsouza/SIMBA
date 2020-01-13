package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @PostMapping("/account")
    public ResponseEntity<String> registerUser (@RequestBody ApplicationUser user){
        log.info("Encrypting password...");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try{
            log.info("Resisting a new user with username '{}'", user.getUsername());
            applicationUserService.registerNewUser(user);
        }catch (Exception e){
            return new ResponseEntity<>("Error to register a new user " + e.getMessage() , HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
