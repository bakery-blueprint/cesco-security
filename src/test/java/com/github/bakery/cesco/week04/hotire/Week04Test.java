package com.github.bakery.cesco.week04.hotire;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextImpl;

import com.github.bakery.cesco.week04.LocalMapSecurityContextRepository;

@SpringBootTest
public class Week04Test {

    @Test
    void LocalMapSecurityContextRepositoryTest() {
        // given
        LocalMapSecurityContextRepository.securityContexts.put("hotire", new SecurityContextImpl());

        // when
        assertThat(LocalMapSecurityContextRepository.securityContexts).isNotEmpty();
    }
}
