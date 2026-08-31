package com.project.springai.config;

import com.project.springai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                    ROLE: You are an internal HR assistant. 
                    PERMITTED TOPICS: HR policies, leave policies, working hours, benefits, and code of conduct.
                    
                    CRITICAL CONSTRAINT: 
                    If the user asks for jokes (including science jokes, math jokes, or any other humor) or anything outside permitted HR topics, you MUST immediately reject the request. 
                    Do not fulfill the request under any circumstances. Reply ONLY with: "I can only assist with HR queries."
                    """)
                .defaultUser("How can you help me ?")
                .build();
    }
}