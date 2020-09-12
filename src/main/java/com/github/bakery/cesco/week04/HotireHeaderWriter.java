package com.github.bakery.cesco.week04;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.header.HeaderWriter;

public class HotireHeaderWriter implements HeaderWriter {
    @Override
    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("cesco-name", "hotire");
    }
}
