package com.ufrn.highlighter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.service.ApplicationUserService;
import com.ufrn.highlighter.util.HttpRequestManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final String server = "http://localhost:5557/";
    private final ObjectMapper mapper = new ObjectMapper();
    private final ApplicationUserService applicationUserService;

    @RequestMapping("/")
    public String index () {return "login";}

    @RequestMapping("/welcome")
    public String welcome (ApplicationUser user, HttpSession session) {
        log.info("Returning initial page...");
        return "welcome";
    }

    @PostMapping("/")
    @SneakyThrows
    public String login (ApplicationUser user, HttpSession session){
        log.info("Realizing the authentication to user '{}'", user.getUsername());
        String data = mapper.writeValueAsString(user);
        HttpResponse<String> response = HttpRequestManager.requestPostOperation(data, server+"login");

        if (response.statusCode() == 200){
            log.info("Successfully authentication, saving session. . .");
            String token = response.headers().firstValue("Authorization").get();
            log.info("token '{}'", token);

            log.info("Getting user id");
            Long id = applicationUserService.getIdFromUsername(user.getUsername());

            log.info("Saving credentials. . .");
            session.setAttribute("applicationUser", user);
            session.setAttribute("token", token);
            session.setAttribute("userId", id);
            session.setAttribute("username", user.getUsername());

            return "welcome";
        }

        return "redirect:/";
    }

}
