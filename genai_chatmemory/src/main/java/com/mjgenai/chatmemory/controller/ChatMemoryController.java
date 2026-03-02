package com.mjgenai.chatmemory.controller;

import com.mjgenai.chatmemory.service.ChatMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatmemory/api")
public class ChatMemoryController {

    @Autowired
    private ChatMemoryService chatMemoryService;

    public ChatMemoryController(ChatMemoryService chatMemoryService) {
        this.chatMemoryService = chatMemoryService;
    }

    @GetMapping("gemini/chat")
    public String geminiChat(@RequestParam String message, @RequestHeader("username") String username) {
        return chatMemoryService.askToAIGemini(message, username);
    }

    @GetMapping("ollama/chat")
    public String ollamaChat(@RequestParam String message, @RequestHeader("username") String username) {
        return chatMemoryService.askToAIOllama(message, username);
    }
}
