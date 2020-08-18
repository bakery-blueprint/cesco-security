package com.github.bakery.cesco.week01.woojin;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bakery.cesco.week01.Role;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class WAccountControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final WAccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser("woojin")
    void create() throws Exception {
        // given
        final WAccount account = new WAccount();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final String json = objectMapper.writeValueAsString(account);

        // when then
        mockMvc.perform(post("/woojin/account")
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
    @Test
    @WithMockUser("woojin")
    void find() throws Exception {
        // given
        final WAccount account = new WAccount();
        account.setUsername("hello");
        account.setPassword("1234");
        account.setRole(Role.USER);

        final WAccount saved = accountService.save(account);

        // when then
        mockMvc.perform(get("/woojin/account/{id}", saved.getId()))
               .andDo(print())
               .andExpect(status().isOk());
    }

    /**
     * TODO 1주차 과제
     * 1. login을 검증하자.
     */
    @Test
    void login() throws Exception {
        // given
        final WAccount account = new WAccount();
        account.setUsername("hello2");
        account.setPassword("1234");
        account.setRole(Role.USER);
        account.encodingPassword(passwordEncoder);

        final WAccount saved = accountService.save(account);

        // Hint SecurityMockMvcRequestBuilders.formLogin() 을 통해 검증
        mockMvc.perform(formLogin().user("hello2").password("1234"))
               .andExpect(authenticated().withRoles("USER"));
    }

}
