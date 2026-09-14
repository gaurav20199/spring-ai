package com.project.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatMemoryController {

    private final ChatClient chatClient;

    public ChatMemoryController(@Qualifier("chatClientWithMemory") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/memory")
    public String getLLMResponse(@RequestHeader String userName, @RequestParam String message) {
        return chatClient.prompt(message).advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID,userName)).call().content();
    }
}
