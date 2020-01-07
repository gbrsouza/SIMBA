package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
