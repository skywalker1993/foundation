package me.huang.sleuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private Client client;

    @GetMapping("/")
    public String foo() {
        return client.foo();
    }
}
