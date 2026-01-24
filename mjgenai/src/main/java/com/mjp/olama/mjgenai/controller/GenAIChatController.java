package com.mjp.olama.mjgenai.controller;

import com.mjp.olama.mjgenai.service.GenAIMultiModelChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multimodel/api")
public class GenAIChatController {

    @Autowired
    private  GenAIMultiModelChatService genAIMultiModelChatService;




    @GetMapping("/gemini/chat")
    public String geminiChat(@RequestParam String message) {

        try {
            return genAIMultiModelChatService.chatWithGemini(message);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam String message) {

        return genAIMultiModelChatService.chatWithOllama(message);
    }
}
