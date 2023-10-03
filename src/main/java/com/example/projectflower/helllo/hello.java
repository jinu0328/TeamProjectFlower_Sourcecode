package com.example.projectflower.helllo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @GetMapping("/test")
    public String hello() {
        return "도화다 웹사이트에 오신것을 진심을 담아서 환영합니다.";
    }
}