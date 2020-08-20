package com.github.bakery.cesco.week02;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/week02")
@RestController
public class Week02Controller {

    @GetMapping("/find")
    public ResponseEntity<String> isOk(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.notFound().build();
        }

        if (principal.getName().equals("week02")) {
            return ResponseEntity.ok(principal.getName());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }
}
