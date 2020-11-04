package me.huang.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
