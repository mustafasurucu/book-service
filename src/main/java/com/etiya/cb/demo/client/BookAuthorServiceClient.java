package com.etiya.cb.demo.client;

import com.etiya.cb.demo.configuration.DownStreamServices;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BookAuthorServiceClient {

    private final WebClient webClient;
    private final DownStreamServices downStreamServices;
    private final CircuitBreaker circuitBreaker;

    public BookAuthorServiceClient(WebClient webClient,
                                   DownStreamServices downStreamServices,
                                   CircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = webClient;
        this.downStreamServices = downStreamServices;
        this.circuitBreaker = circuitBreakerFactory.create("book-author-service-circuit-breaker");
    }

    public String getBookAuthor() {
        return circuitBreaker.run(() ->
                        webClient
                                .get()
                                .uri(downStreamServices.bookAuthorServiceUrl())
                                .retrieve()
                                .bodyToMono(String.class)
                                .block(),
                throwable -> getDefaultBookAuthor());
    }

    public String getDefaultBookAuthor() {
        return "Author of the book from book-service";
    }
}
