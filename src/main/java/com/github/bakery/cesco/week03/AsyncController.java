package com.github.bakery.cesco.week03;

import com.github.bakery.cesco.week01.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@Slf4j
@RequestMapping("/async")
@RestController
@RequiredArgsConstructor
public class AsyncController {
    private final AccountService accountService;

    @GetMapping("/callable")
    public Callable<String> callable() {
        return () -> {
            log.info("callable");
            return "hello";
        };
    }

}
