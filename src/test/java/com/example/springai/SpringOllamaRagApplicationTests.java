package com.example.springai;

import com.example.springai.mcp.PocMcpTools;
import org.junit.jupiter.api.Test;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SpringOllamaRagApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void chatControllerBeanExists() {
        assertThat(context.getBeanNamesForType(org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.class)).isNotEmpty();
    }

    @Test
    void pocMcpToolsBeanExists() {
        assertThat(context.getBean(PocMcpTools.class)).isNotNull();
    }

    @Test
    void toolCallbackProviderBeanExists() {
        assertThat(context.getBean(ToolCallbackProvider.class)).isNotNull();
    }
}
