package com.mjp.openai.myai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderAIAssistantService {

    private final ChatClient chatClient;

    @Value("classpath:templates/order_system_template.st")
    private Resource orderSystemPrompt;

    @Value("classpath:templates/order_user_template.st")
    private Resource orderUserPrompt;

    @Value("classpath:templates/order_system_policy.st")
    private Resource orderSystemPolicyPrompt;


    public OrderAIAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String assistWithOrderSupport(String customerName, String orderId,String customerMessage) {

        return chatClient
                .prompt()
                .system(orderSystemPrompt)
                .user(promptUserSpec -> promptUserSpec.text(orderUserPrompt)
                        .param("customerName",customerName)
                         .param("orderId",orderId)
                        .param("customerMessage",customerMessage))
                .call()
                .content();
    }
    public String talkToAISupport(String customerName, String orderId, String customerMessage) {
        return chatClient
                .prompt()
                .system(orderSystemPolicyPrompt)
                .user(promptUserSpec -> promptUserSpec.text(orderUserPrompt)
                        .param("customerName", customerName)
                        .param("orderId", orderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();
    }
}
