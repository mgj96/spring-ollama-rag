package com.example.springai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Ollama 채팅 REST API.
 * GET /chat?message=... — 전체 응답 한 번에 반환.
 * GET /chat/stream?message=... — 스트리밍 응답 (UTF-8, text/plain으로 한 줄 이어서 전송).
 */
@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

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

    @GetMapping(value = "/chat/stream", produces = "text/plain;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam(defaultValue = "안녕") String message) {
        log.info("스트리밍 시작: message={}", message);
        return chatClient.prompt()
                .user(message)
                .stream()
                .content()
                .doOnNext(chunk -> System.out.print(chunk))
                .doOnComplete(() -> System.out.println())
                .doOnError(e -> log.warn("스트리밍 오류: {}", e.getMessage()));
    }
}
