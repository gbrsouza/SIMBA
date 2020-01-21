package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    @PostMapping("/account")
    public String registerUser (ApplicationUser user){
        log.info("Encrypting password...");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try{
            log.info("Resisting a new user with username '{}'", user.getUsername());
            applicationUserService.registerNewUser(user);
            return "redirect:login";
        }catch (Exception e){
            return "redirect:account?error";
        }
    }

}
