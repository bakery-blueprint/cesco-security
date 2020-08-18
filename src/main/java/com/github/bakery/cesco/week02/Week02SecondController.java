package com.github.bakery.cesco.week02;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/week02/second")
@RestController
public class Week02SecondController {

    @GetMapping
    public ResponseEntity<String> second(Principal principal) {
        if (principal != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("unauthentication");
    }
}
