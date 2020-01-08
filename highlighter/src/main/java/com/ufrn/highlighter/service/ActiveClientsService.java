package com.ufrn.highlighter.service;

import com.ufrn.highlighter.model.ActiveClients;
import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.repository.ActiveClientsRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActiveClientsService {

    private final ActiveClientsRepository activeClientsRepository;
    public void insertActiveClient (ActiveClients activeClients) {activeClientsRepository.save(activeClients);}
    public void deleteByUsername (String username) {activeClientsRepository.deleteByUsername(username);}


}
