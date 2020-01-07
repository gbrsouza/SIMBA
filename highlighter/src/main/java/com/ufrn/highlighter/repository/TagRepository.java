package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
