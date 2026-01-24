package com.mjp.olama.mjgenai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GenAIMultiModelChatService {

    //private final ChatClient chatClient;
    private final ChatClient googleGenAiChatClient;
    private final ChatClient ollamaAIChatClient;


    public GenAIMultiModelChatService(ChatClient googleGenAiChatClient, ChatClient ollamaAIChatClient) {
        this.googleGenAiChatClient = googleGenAiChatClient;
        this.ollamaAIChatClient = ollamaAIChatClient;
    }

    public String chatWithGemini(String message) {
        System.out.println("Received message for Gemini: " + googleGenAiChatClient.prompt());
        return googleGenAiChatClient
                .prompt(message)
                .call()
                .content();
    }

    public String chatWithOllama(String message) {
        return ollamaAIChatClient
                .prompt(message)
                .call()
                .content();
    }
}