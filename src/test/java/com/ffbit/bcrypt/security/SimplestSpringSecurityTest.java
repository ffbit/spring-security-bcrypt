package com.ffbit.bcrypt.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/spring/test-application-context.xml")
public class SimplestSpringSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    private final String SECURED_URI = "/";

    private final String LOGIN_PAGE_URL = "http://localhost/spring_security_login";

    private final String LOGIN_PROCESSING_URI = "/j_spring_security_check";

    @Test
    public void itShouldDenyAnonymousAccess() throws Exception {
        mockMvc.perform(get(SECURED_URI))
                .andExpect(redirectedUrl(LOGIN_PAGE_URL));
    }

    @Test
    public void itShouldDenyAccessForNotRegisteredUser() throws Exception {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken("bad_user", "bad_pwd");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext);

        mockMvc.perform(get(SECURED_URI).session(session))
                .andExpect(redirectedUrl(LOGIN_PAGE_URL));
    }

    @Test
    public void itShouldAllowAccessRoleUser() throws Exception {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken("user", "user_pwd");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext);

        mockMvc.perform(get(SECURED_URI).session(session))
                .andExpect(status().isOk());
    }

}
