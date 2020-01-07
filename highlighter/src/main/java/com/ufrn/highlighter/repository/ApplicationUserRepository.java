package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
