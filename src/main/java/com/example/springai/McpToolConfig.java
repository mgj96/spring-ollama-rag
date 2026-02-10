package com.example.springai;

import com.example.springai.mcp.PocMcpTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** MCP 서버에 @Tool 빈을 노출하는 설정. */
@Configuration
public class McpToolConfig {

    @Bean
    public ToolCallbackProvider mcpToolCallbackProvider(PocMcpTools pocMcpTools) {
        return MethodToolCallbackProvider.builder().toolObjects(pocMcpTools).build();
    }
}
