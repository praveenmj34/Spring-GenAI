package com.mjp.openai.myai.controller;

import com.mjp.openai.myai.service.OrderAIAssistantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/ai-assistant")
public class OrderAIAssistantController {

private OrderAIAssistantService orderAIAssistantService;

    public OrderAIAssistantController(OrderAIAssistantService orderAIAssistantService) {
        this.orderAIAssistantService = orderAIAssistantService;
    }
    @GetMapping("/order-support")
    public String getOrderSupportResponse(@RequestParam String customerName, @RequestParam String orderId, @RequestParam String customerMessage) {
        return orderAIAssistantService.assistWithOrderSupport(customerName, orderId, customerMessage);
    }

    @GetMapping("/order-ai-support")
    public String talkToOrderAISupport(@RequestParam String customerName, @RequestParam String orderId,@RequestParam String customerMessage) {
        return orderAIAssistantService.talkToAISupport(customerName, orderId, customerMessage);
    }
}
