package com.example.springai.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/** MCP로 노출하는 POC 도구 (getProjectInfo, add). */
@Component
public class PocMcpTools {

    @Tool(description = "이 Spring AI POC 프로젝트의 이름과 버전 정보를 반환합니다.")
    public String getProjectInfo() {
        return "프로젝트: spring-ollama-rag, 버전: 0.0.1-SNAPSHOT (Spring AI 학습·POC용)";
    }

    @Tool(description = "두 정수를 더한 결과를 반환합니다.")
    public int add(
            @ToolParam(description = "첫 번째 수", required = true) int a,
            @ToolParam(description = "두 번째 수", required = true) int b) {
        return a + b;
    }
}
