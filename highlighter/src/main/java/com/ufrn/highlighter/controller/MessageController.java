package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/project/messages/tag/{id}")
    public String addTag (@PathVariable("id") Long id,
                          @RequestParam("tag") String tag,
                          Model model,
                          @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size)
    {
        log.info("add tag in message id = '{}'", id);
        var message = messageService.listMessageById(id);
        message.setTag(tag);
        messageService.update(message);

        String url = "redirect:/project/messages/" +
                message.getProject().getId().toString() +
                "?size=" + size.get().toString() +
                "&page=" + (page.get()+1);

        return url;
    }
}
