package com.xufei.demo.controller;


import com.xufei.demo.test.thread.example.v2.UseDemoV2;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private UseDemoV2 useDemoV2;

    @GetMapping("/test")
    public String test(){
        useDemoV2.submitTask();
        return "hello world";
    }
}
