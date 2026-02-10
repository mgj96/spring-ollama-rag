package com.example.springai.mcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PocMcpToolsTest {

    private final PocMcpTools tools = new PocMcpTools();

    @Test
    void getProjectInfoReturnsNonEmpty() {
        String info = tools.getProjectInfo();
        assertThat(info).contains("spring-ollama-rag").contains("0.0.1-SNAPSHOT");
    }

    @Test
    void addReturnsSum() {
        assertThat(tools.add(2, 3)).isEqualTo(5);
        assertThat(tools.add(-1, 1)).isEqualTo(0);
    }
}
