package com.ufrn.highlighter.service;

import com.ufrn.highlighter.model.Message;
import com.ufrn.highlighter.model.Project;
import com.ufrn.highlighter.repository.MessageRepository;
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
public class MessageService {

    private final MessageRepository messageRepository;

    public void insert (Message message) {messageRepository.save(message);}
    public List<Message> listAllMessagesFromProject (Project project) {
        // @TODO
        return null;
    }

    public Message listMessageById (Long id) {return messageRepository.findById(id).get();}

    @Transactional
    @Modifying
    public void update (Message message) {messageRepository.save(message);}

    @Transactional
    @Modifying
    public void delete (Message message) {messageRepository.delete(message);}

}
