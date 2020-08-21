package com.github.bakery.cesco.week01.woojin;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/woojin")
public class WSampleController {

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
