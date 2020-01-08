package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody ApplicationUser user){
        if (!authenticationService.isRegister(user))
            return new ResponseEntity<>("Error in authentication - invalid credentials", HttpStatus.NOT_FOUND);
        else return null;
    }

}
