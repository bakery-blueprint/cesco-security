package com.github.bakery.cesco.week01.woojin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/woojin/account")
@RestController
@RequiredArgsConstructor
public class WAccountController {
    private final WAccountService WAccountService;

    @PostMapping
    public ResponseEntity<WAccount> create(@RequestBody WAccount WAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(WAccountService.save(WAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WAccount> findById(@PathVariable Long id) {
        return ResponseEntity.ok(WAccountService.findById(id));
    }
}
