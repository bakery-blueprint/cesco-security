package com.github.bakery.cesco.week01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@RestController
public class SampleController {

    @GetMapping("/info")
    public ModelAndView info(final ModelAndView modelAndView, final Principal principal) {
        Optional.ofNullable(principal)
                .map(Principal::getName)
                .or(() -> Optional.of("Hello"))
                .ifPresent(message -> modelAndView.addObject("message", message));

        modelAndView.setViewName("info");

        return modelAndView;
    }
}
