package com.github.bakery.cesco.week01;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private final AccountService accountService;

    @Test
    void create() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final String json = objectMapper.writeValueAsString(account);

        // when then
        mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andDo(print())
               .andExpect(status().isCreated());
    }


    /**
     * TODO 1주차 과제
     * 1. 201 응답이 오도록 변경해보자.
     * Hint wit user
     */
    @Disabled
    @Test
    void find() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final Account saved = accountService.save(account);

        // when then
        mockMvc.perform(get("/account/" + saved.getId()))
               .andDo(print())
               .andExpect(status().isCreated());
    }

    /**
     * TODO 1주차 과제
     * 1. login을 검증하자.
     */
    @Disabled
    @Test
    void login() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final Account saved = accountService.save(account);

        // Hint SecurityMockMvcRequestBuilders.formLogin() 을 통해 검증
    }


}