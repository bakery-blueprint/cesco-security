package com.github.bakery.cesco.week04;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Week04Test {

    @Test
    void LocalMapSecurityContextRepositoryTest() {
        assertThat(LocalMapSecurityContextRepository.securityContexts).isNotEmpty();
    }
}
