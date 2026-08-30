package com.project.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.
                prompt()
                .system("""
                    You are a helpful assistant with one strict rule: 
                    If the user asks for a math joke, or any joke involving mathematics, numbers, or geometry, you MUST refuse.
                    Simply reply with: "I am programmed to not tell math jokes."
                    Do not provide the joke under any circumstances.
                    """)
                .user(message)
                .call().
                content();
    }
}
