package com.ufrn.highlighter.service;

import com.ufrn.highlighter.model.Message;
import com.ufrn.highlighter.model.Project;
import com.ufrn.highlighter.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
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

    public List<Message> getMessageByProjectId (Long id){
        return projectRepository.findById(id).get().getMessages();
    }

    public Project getProjectById (Long id) {return projectRepository.findById(id).get();}

    @Transactional
    @Modifying
    public void update (Project project) {projectRepository.save(project);}

    @Transactional
    @Modifying
    public void delete (Project project) {projectRepository.delete(project);}

    public Page<Message> findPaginatedMessagesByProjectId (Pageable pageable, Long id){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Message> messages = getMessageByProjectId(id);
        List<Message> list;

        if (messages.size() < startItem)
            list = Collections.emptyList();
        else {
            int toIndex = Math.min(startItem + pageSize, messages.size());
            list = messages.subList(startItem, toIndex);
        }

        Page<Message> messagePage = new PageImpl<Message>(list, PageRequest.of(currentPage, pageSize), messages.size());
        return messagePage;
    }

}

