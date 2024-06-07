package covy.threadlocal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread-local")
@RequiredArgsConstructor
public class ThreadLocalController {

    @GetMapping("/test1")
    public String threadLocalTest1(String testName) {
        return "sucess -> " + testName;
    }

}
