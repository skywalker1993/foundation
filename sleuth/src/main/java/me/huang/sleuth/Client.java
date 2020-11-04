package me.huang.sleuth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("feign-client")
public interface Client {

    @GetMapping("/")
    String foo();
}
