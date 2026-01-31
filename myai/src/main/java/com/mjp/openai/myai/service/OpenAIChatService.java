package com.mjp.openai.myai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatService {

   // @Qualifier("googleGenAiClient")
    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    public String chatWithLLM(String message) {
        return chatClient.prompt(message).call().content();
    }
}
