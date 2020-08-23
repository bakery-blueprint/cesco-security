package com.github.bakery.cesco.week02.hotire;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class AuthenticationProviderTest {
    private final MockMvc mockMvc;

    @Test
    void isOk() throws Exception {
        mockMvc.perform(formLogin().user("week02").password("123"))
               .andDo(print())
               .andExpect(authenticated().withRoles("ADMIN"));
    }

    @Test
    void secondIsOk() throws Exception {
        // when then
        mockMvc.perform(get("/week02/second")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "week02", roles = "ADMIN")
    void userTest() throws Exception {

        // when then
        mockMvc.perform(get("/week02/user")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "week02", roles = "ADMIN")
    void accessDeniedTest() throws Exception {
        // when then
        mockMvc.perform(get("/week02/accessDenied")
                                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().is(4003));
    }
}
