package com.ufrn.highlighter.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.ufrn.highlighter.model.Message;
import com.ufrn.highlighter.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import java.util.List;
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

    @GetMapping("/project/export")
    @SneakyThrows
    public void exportCSV (@RequestParam("id") Long id, HttpServletResponse response){
        List<Message> messages = messageService.listMessageByProjectIdAndTagNotNull(id);
        String filename = "classified-messages-project" + id + ".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<Message> writer = new StatefulBeanToCsvBuilder<Message>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator('|')
                .withOrderedResults(false)
                .build();

        //write all messages to csv file
        writer.write(messages);
//        return "redirect:/project";
    }
}
