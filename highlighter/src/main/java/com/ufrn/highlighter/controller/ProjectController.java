package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.model.Project;
import com.ufrn.highlighter.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/project")
    public ModelAndView initialProject (){

        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("project");
        Iterable<Project> projects = projectService.listAllProjectsByUserId(user.getId());
        mv.addObject("projects", projects);

        return mv;
    }

    @GetMapping("/project/messages")
    public String messages (){
        return "message";
    }


}
