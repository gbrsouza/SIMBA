package com.ufrn.highlighter.repository;

import com.ufrn.highlighter.model.ActiveClients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ActiveClientsRepository extends JpaRepository<ActiveClients, Long> {

    @Transactional
    @Modifying
    @Query ("DELETE FROM ActiveClients WHERE username = ?1 ")
    void deleteByUsername(String username);
}
