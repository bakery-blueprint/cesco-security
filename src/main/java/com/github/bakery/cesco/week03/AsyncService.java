package com.github.bakery.cesco.week03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
public class AsyncService implements Supplier<String> {

    public AsyncService() {
        log.info("");
    }

    @Async
    public void async() {
        log.info("hello");
    }

    @Override
    public String get() {
        return "null";
    }
}
