package com.example.springai;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Ollama 호출용 HTTP 클라이언트 타임아웃 설정.
 * application.yml의 app.http.connect-timeout, app.http.read-timeout 값을 사용함.
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient(HttpClientProperties properties) {
        Duration connectTimeout = Duration.ofSeconds(properties.getConnectTimeout());
        return HttpClient.newBuilder()
                .connectTimeout(connectTimeout)
                .build();
    }

    @Bean
    public RestClient.Builder restClientBuilder(HttpClient httpClient, HttpClientProperties properties) {
        Duration readTimeout = Duration.ofSeconds(properties.getReadTimeout());
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(readTimeout);
        return RestClient.builder().requestFactory(requestFactory);
    }

    @Bean
    public WebClient.Builder webClientBuilder(HttpClient httpClient, HttpClientProperties properties) {
        Duration readTimeout = Duration.ofSeconds(properties.getReadTimeout());
        JdkClientHttpConnector connector = new JdkClientHttpConnector(httpClient);
        connector.setReadTimeout(readTimeout);
        return WebClient.builder().clientConnector(connector);
    }
}
