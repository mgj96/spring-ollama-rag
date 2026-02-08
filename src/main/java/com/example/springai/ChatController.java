package com.example.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Ollama 채팅 REST API. GET /chat?message=... */
@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(defaultValue = "안녕") String message) {
        try {
            String content = chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
            return ResponseEntity.ok(content);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Ollama에 연결할 수 없습니다. 로컬에서 Ollama가 실행 중인지 확인하세요. 원인: " + e.getMessage());
        }
    }
}
