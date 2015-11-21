package cz.zjor.toys.polychat.controller;

import cz.zjor.toys.polychat.auth.User;
import cz.zjor.toys.polychat.model.Message;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class ChatController {

    private List<Message> messages = new ArrayList<>();

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> list(@AuthenticationPrincipal User user) {
        return messages;
    }

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@AuthenticationPrincipal User user, @RequestParam("content") String content) {
        messages.add(new Message(user, content));
        return "redirect:/chat";
    }


}
