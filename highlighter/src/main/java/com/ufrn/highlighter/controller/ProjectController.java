package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.model.Message;
import com.ufrn.highlighter.model.Project;
import com.ufrn.highlighter.service.ApplicationUserService;
import com.ufrn.highlighter.service.MessageService;
import com.ufrn.highlighter.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;
    private final ApplicationUserService applicationUserService;
    private final MessageService messageService;

    @GetMapping("/project")
    public ModelAndView initialProject (){

        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("project");
        Iterable<Project> projects = projectService.listAllProjectsByUserId(user.getId());
        mv.addObject("projects", projects);

        return mv;
    }

    @GetMapping("/project/create")
    public ModelAndView createProject (){
        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mv = new ModelAndView("create-project");
        var usersList = applicationUserService.getAllUsers();
        usersList.remove(user);
        mv.addObject("users", usersList);
        return mv;
    }

    @PostMapping("/project/create")
    public String saveProject (Project project) {
        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        project.setOwner(user);
        project.getApplicationUsers().add(user);

        projectService.insert(project);
        return "redirect:/project";
    }

    @GetMapping("/project/messages/{id}")
    public String messages (@PathVariable("id") Long id,
                                  Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);

        Page<Message> messagePage = projectService.findPaginatedMessagesByProjectId(
                PageRequest.of(currentPage-1, pageSize), id);

        model.addAttribute("messagePage", messagePage);
        model.addAttribute("projectId", id);
        int totalPages = messagePage.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "message";
//        ModelAndView mv = new ModelAndView("message");
//        List<Message> messages = projectService.getMessageByProjectId(id);
//        mv.addObject("messages", messages);
//        mv.addObject("numberMessages", messages.size());
//        return mv;
    }



}
