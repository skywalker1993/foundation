package me.huang.hystrixclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
@Slf4j
public class HystrixClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixClientApplication.class, args);
	}

	@HystrixCommand(fallbackMethod = "fallback",
		commandProperties = {
			@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "100")
		}
	)
	@GetMapping("/")
	public String doSomething() {
		return "something";
	}

	@HystrixCommand(commandKey = "other", fallbackMethod = "fallback")
	@GetMapping("/other")
	public String doOtherThing() {
		return "other";
	}

	public String fallback() {
		log.info("circuit close");
		return "fallback";
	}
}
