package com.github.bakery.cesco.week01;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
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

    @Test
    void find() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final Account saved = accountService.save(account);

        // when then
        mockMvc.perform(get("/account/" + saved.getId()).with(user("hotire").roles("USER")))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        // given
        final Account account = new Account();
        account.setUsername(UUID.randomUUID().toString());
        account.setPassword("1234");
        account.setRole(Role.USER);

        final Account saved = accountService.save(account);

        mockMvc.perform(formLogin().user(saved.getUsername()).password("1234"))
               .andExpect(authenticated());
    }


}