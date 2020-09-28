package com.github.bakery.cesco.week04;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class Week04Test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void LocalMapSecurityContextRepositoryTest() throws Exception {
        mockMvc.perform(get("/week04/find").queryParam("username", "woojin").with(user("woojin")).contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
        assertThat(LocalMapSecurityContextRepository.securityContexts).isNotEmpty();
    }


    @Test
    void headerWriterTest() throws Exception {
        mockMvc.perform(get("/week04/find").queryParam("username", "woojin").with(user("woojin")).contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk())
        .andExpect(header().exists("woojin"));
    }
}
