package com.mjp.openai.myai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvestmentAdvisorAssistantService {

    private final ChatClient chatClient;

    @Value("classpath:templates/sebi_investment_advisor_system_policy.st")
    private Resource investmentAdvSystemPolicyPrompt;

    @Value("classpath:templates/user_investment_decisions.st")
    private Resource investmentAdvUserPrompt;

    public InvestmentAdvisorAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getInvestmentAdvice(String customerName, String investmentGoal, String riskTolerance) {
        String prompt = String.format(
            "You are an investment advisor. Provide advice to %s who has the following investment goal: %s and risk tolerance: %s.",
            customerName, investmentGoal, riskTolerance
        );
       String[] recommendedInvestmentRange = findInvestmentRange(investmentGoal).split(" to ");
        return chatClient
                .prompt()
                .system(investmentAdvSystemPolicyPrompt)
                .user(promptUserSpec -> promptUserSpec.text(investmentAdvUserPrompt)
                        .param("investorName", customerName)
                        .param("startSalRange", recommendedInvestmentRange[0])
                        .param("endSalRange", recommendedInvestmentRange[1])
                        .param("riskTolerance", riskTolerance)
                        .param("advisorName","Praveen Kumar M J")
                        .param("advisorRegNo","INZ1234567890")
                        .param("date", new Date().toString())
                        .param("assetOrInstrumentName", determineAssetAllocation(riskTolerance)))
                .call()
                .content();
    }

    private String findInvestmentRange(String investmentGoal) {
        // Dummy implementation for illustration
        if (investmentGoal.equalsIgnoreCase("retirement")) {
            return "100,000 to 500,000 INR";
        } else if (investmentGoal.equalsIgnoreCase("education")) {
            return "50,000 to 200,000 INR";
        } else {
            return "10,000 to 100,000 INR";
        }
    }

    private String determineAssetAllocation(String riskTolerance) {
        // Dummy implementation for illustration
        if (riskTolerance.equalsIgnoreCase("high")) {
            return "80% stocks, 15% bonds, 5% cash";
        } else if (riskTolerance.equalsIgnoreCase("medium")) {
            return "60% stocks, 30% bonds, 10% cash";
        } else {
            return "40% stocks, 50% bonds, 10% cash";
        }
    }
}
