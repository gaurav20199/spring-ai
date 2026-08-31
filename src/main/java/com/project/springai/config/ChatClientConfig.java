package com.project.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
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