package me.huang.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private Client client;

    @GetMapping("/")
    public String feignFoo() {
        return client.foo();
    }
}
