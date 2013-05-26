package com.ffbit.bcrypt.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/spring/test-application-context.xml")
public class SimplestSpringSecurityTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    private final String SECURED_URI = "/";

    private final String LOGIN_PAGE = "http://localhost/spring_security_login";

    private final String LOGIN_PROCESSING_URI = "/j_spring_security_check";

    @Test
    public void itShouldRedirectToLoginUrl() throws Exception {
        mockMvc.perform(get(SECURED_URI))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(LOGIN_PAGE));
    }

}
