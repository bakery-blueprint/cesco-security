package com.github.bakery.cesco.week01;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class AccountControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final String json = objectMapper.writeValueAsString(account);

        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andDo(print())
               .andExpect(status().isCreated());
    }


}