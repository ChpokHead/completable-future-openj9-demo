package com.chpok.completablefutureopenj9.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@Slf4j
public class CompletableFutureController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CompletableFutureController.class);
    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(500);

    @GetMapping(path = "/cf-demo/{seconds}")
    public CompletableFuture<ResponseEntity<String>> demo(@PathVariable int seconds) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(seconds).toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            final String threadName = Thread.currentThread().getName();

            LOGGER.info(threadName);

            return threadName;
        }, EXECUTOR).thenApply(ResponseEntity::ok);
    }
}
