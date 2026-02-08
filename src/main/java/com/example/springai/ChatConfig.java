package com.example.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI ChatClient 공통 빈 설정.
 * - Ollama 스타터가 제공하는 ChatClient.Builder를 한 곳에서 build()하여
 *   ChatClient 빈으로 등록. 컨트롤러·서비스에서는 ChatClient만 주입받으면 됨.
 */
@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }
}
