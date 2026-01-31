package com.mjp.openai.myai.controller;


import com.mjp.openai.myai.service.MessageRolesDemoService;
import com.mjp.openai.myai.service.OpenAIChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gemini/api")
public class OpenAIChatController {


    private final OpenAIChatService openAIChatService;
    private final MessageRolesDemoService messageRolesDemoService;

    public OpenAIChatController(OpenAIChatService openAIChatService, MessageRolesDemoService messageRolesDemoService) {
        this.openAIChatService = openAIChatService;
        this.messageRolesDemoService = messageRolesDemoService;
    }


    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return openAIChatService.chatWithLLM(message);
    }

    @GetMapping("/checkpolicy")
    public String checkPolicy(@RequestParam String message) {
        return messageRolesDemoService.checkPolicy(message);
    }

    @GetMapping("/checkpolicyV2")
    public String checkPolicyV2(@RequestParam String message) {
        return messageRolesDemoService.checkInsuranceV2Policy(message);
    }

    @GetMapping("/checkpolicyV3")
    public ChatResponse checkPolicyV3(@RequestParam String message) {
        return messageRolesDemoService.checkInsuranceV3Policy(message);
    }
}
