package com.mjgenai.chatmemory.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ChatMemoryService {

    private final ChatClient googleGenAiChatClient;
    private final ChatClient ollamaAIChatClient;

    public ChatMemoryService(ChatClient googleGenAiChatClient, ChatClient ollamaAIChatClient) {
        this.googleGenAiChatClient = googleGenAiChatClient;
        this.ollamaAIChatClient = ollamaAIChatClient;
    }

    public String askToAIGemini(String message, String username) {
        return googleGenAiChatClient
                .prompt(message)
                .advisors(adviceSpec ->
                        adviceSpec.param(CONVERSATION_ID, username)
                )
                .call()
                .content();
    }

    public String askToAIOllama(String message, String username) {
        return ollamaAIChatClient
                .prompt(message)
                .advisors(adviceSpec ->
                        adviceSpec.param(CONVERSATION_ID, username)
                )
                .call()
                .content();
    }

}
