package com.blog.study.auto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("url-auto")
    public String test() {
        System.out.println("url-auto-call");
        return "HelloWorld";
    }
}
