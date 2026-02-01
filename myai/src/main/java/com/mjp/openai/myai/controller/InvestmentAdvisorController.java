package com.mjp.openai.myai.controller;

import com.mjp.openai.myai.service.InvestmentAdvisorAssistantService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investment/advisor")
public class InvestmentAdvisorController {

    private InvestmentAdvisorAssistantService investmentAdvisorAssistantService;
    public InvestmentAdvisorController(InvestmentAdvisorAssistantService investmentAdvisorAssistantService) {
        this.investmentAdvisorAssistantService = investmentAdvisorAssistantService;
    }

    @GetMapping("/get-advice")
    public String  getInvestmentAdvisorAssistantService(String customerName, String investmentGoal, String riskTolerance) {
        return investmentAdvisorAssistantService.getInvestmentAdvice(customerName, investmentGoal, riskTolerance);
    }
}
