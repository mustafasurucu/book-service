package com.etiya.cb.demo.client;

import com.etiya.cb.demo.configuration.DownStreamServices;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BookNameServiceClient {

    private final WebClient webClient;
    private final DownStreamServices downStreamServices;
    private final CircuitBreaker circuitBreaker;

    public BookNameServiceClient(WebClient webClient,
                                 DownStreamServices downStreamServices,
                                 CircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = webClient;
        this.downStreamServices = downStreamServices;
        this.circuitBreaker = circuitBreakerFactory.create("book-name-service-circuit-breaker");
    }

    public String getBookName() {
        return circuitBreaker.run(() ->
                        webClient
                                .get()
                                .uri(downStreamServices.bookNameServiceUrl())
                                .retrieve()
                                .bodyToMono(String.class)
                                .block(),
                throwable -> getDefaultBookName());
    }

    public String getDefaultBookName() {
        return "Name of the book from book-service";
    }
}
