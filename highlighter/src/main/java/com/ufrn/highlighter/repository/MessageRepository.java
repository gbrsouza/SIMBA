package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByTagIsNotNullAndProject_Id(Long id);

}
