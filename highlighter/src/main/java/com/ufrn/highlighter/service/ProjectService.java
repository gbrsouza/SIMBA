package com.ufrn.highlighter.service;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.model.Project;
import com.ufrn.highlighter.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void insert(Project project) { projectRepository.save(project); }

    public List<Project> listAllProjectsByUserId (Long id) {
        return projectRepository.findAllByApplicationUsers_Id(id);
    }

    public Project getProjectById (Long id) {return projectRepository.findById(id).get();}

    @Transactional
    @Modifying
    public void update (Project project) {projectRepository.save(project);}

    @Transactional
    @Modifying
    public void delete (Project project) {projectRepository.delete(project);}

}

