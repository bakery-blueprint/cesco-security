package com.github.bakery.cesco.week02;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bakery.cesco.week01.AccountService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class AuthenticationProviderTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    // TODO : 2주차 과제 authenticationProvider 테스트
    // username이 "week02"로 UserDetailsService에서 생성될 수 있게 셋팅하고 해당 테스트를 통과해보자.
    @Test
    @Disabled
    void isOk() throws Exception {

        // when then
        mockMvc.perform(get("/week02/find")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    // TODO : 2주차 과제 filterChainProxy 테스트
    // "/week02/second"로 요청하는 경우 permitAll이 될 수 있게 FilterChainProxy쪽 관련 설정을 변경해보자.
    // hint : SecurityFilterChain
    @Test
    @Disabled
    void secondIsOk() throws Exception {

        // when then
        mockMvc.perform(get("/week02/second")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    // TODO : 2주차 과제 AccessDecisionManager 테스트
    // "/week02/user"로 ADMIN Role을 가진 유저가 접근했을 때도 isOk가 될 수 있게 변경해보자.
    // hint : SecurityFilterChain
    @Test
    @Disabled
    @WithMockUser(username = "week02", roles = "ADMIN")
    void userTest() throws Exception {

        // when then
        mockMvc.perform(get("/week02/user")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }
}
