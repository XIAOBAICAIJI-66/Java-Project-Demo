package com.xufei.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTest() throws Exception {
        // 发送 GET 请求到 /demo/test 路径
        mockMvc.perform(get("/demo/test"))
                // 期望响应状态码为 200 OK
              .andExpect(status().isOk())
                // 期望响应内容为 "hello world"
              .andExpect(content().string("hello world"));
    }
}
