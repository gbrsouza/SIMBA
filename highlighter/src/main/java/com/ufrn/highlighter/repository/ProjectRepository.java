package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
