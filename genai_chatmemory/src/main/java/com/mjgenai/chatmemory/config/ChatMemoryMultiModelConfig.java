package com.mjgenai.chatmemory.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatMemoryMultiModelConfig {

    @Bean
    public ChatMemory chatmemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(5).chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    @Bean
    public ChatClient googleGenAiChatClient(GoogleGenAiChatModel googleGenAiChatModel, ChatMemory chatMemory) {
        Advisor memoryAdvisor= MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(googleGenAiChatModel).defaultAdvisors(List.of(new SimpleLoggerAdvisor(),memoryAdvisor)).build();
    }

    @Bean
    public ChatClient ollamaAIChatClient(OllamaChatModel ollamaChatModel, ChatMemory chatMemory) {
        Advisor memoryAdvisor= MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(ollamaChatModel).defaultAdvisors(List.of(new SimpleLoggerAdvisor(),memoryAdvisor)).build();
    }
}
