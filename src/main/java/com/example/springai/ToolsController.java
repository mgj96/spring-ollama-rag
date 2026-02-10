package com.example.springai;

import com.example.springai.mcp.PocMcpTools;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** PocMcpTools REST API (MVP). */
@RestController
@RequestMapping("/api/tools")
public class ToolsController {

    private static final List<Map<String, Object>> TOOL_LIST = List.of(
            Map.<String, Object>of(
                    "name", "getProjectInfo",
                    "description", "이 Spring AI POC 프로젝트의 이름과 버전 정보를 반환.",
                    "path", "/api/tools/project-info"
            ),
            Map.<String, Object>of(
                    "name", "add",
                    "description", "두 정수를 더한 결과를 반환합니다.",
                    "path", "/api/tools/add",
                    "parameters", List.of(Map.of("name", "a", "required", true), Map.of("name", "b", "required", true))
            )
    );

    private final PocMcpTools pocMcpTools;

    public ToolsController(PocMcpTools pocMcpTools) {
        this.pocMcpTools = pocMcpTools;
    }

    @GetMapping
    public List<Map<String, Object>> listTools() {
        return TOOL_LIST;
    }

    @GetMapping("/project-info")
    public ResponseEntity<Map<String, String>> projectInfo() {
        return ResponseEntity.ok(Map.of("info", pocMcpTools.getProjectInfo()));
    }

    @GetMapping("/add")
    public ResponseEntity<Map<String, Integer>> add(@RequestParam int a, @RequestParam int b) {
        return ResponseEntity.ok(Map.of("result", pocMcpTools.add(a, b)));
    }
}
