package com.sehrish.FirstSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
         return  "hello world!";
    }

    @PostMapping("/hello")
    public String helloPost(@RequestBody String name) {
        System.out.println("POST /hello HIT");
        return  "hello world!" + name +"!";
    }
}
