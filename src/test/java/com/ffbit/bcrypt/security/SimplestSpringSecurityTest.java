package com.ffbit.bcrypt.security;

import com.ffbit.bcrypt.domain.User;
import com.ffbit.bcrypt.domain.UserRole;
import com.ffbit.bcrypt.repository.UserRepository;
import org.junit.Before;
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
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration("file:src/test/resources/spring/test-application-context.xml")
public class SimplestSpringSecurityTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private final String SECURED_URI = "/";

    private final String LOGIN_PAGE_URL = "http://localhost/spring_security_login";

    @Before
    public void setUp() throws Exception {
        User user = new User("user", "user_pwd");
        user.grantAuthority(UserRole.ROLE_USER);

        userRepository.save(user);
    }

    @Test
    public void itShouldDenyAnonymousAccess() throws Exception {
        mockMvc.perform(get(SECURED_URI))
                .andExpect(redirectedUrl(LOGIN_PAGE_URL));
    }

    @Test
    public void itShouldDenyAccessForNotRegisteredUser() throws Exception {
        MockHttpSession session =
                authenticateUserWithLoginAndPassword("bad_user", "bad_pwd");

        mockMvc.perform(get(SECURED_URI).session(session))
                .andExpect(redirectedUrl(LOGIN_PAGE_URL));
    }

    @Test
    public void itShouldAllowAccessRoleUser() throws Exception {
        MockHttpSession session =
                authenticateUserWithLoginAndPassword("user", "user_pwd");

        mockMvc.perform(get(SECURED_URI).session(session))
                .andExpect(status().isOk());
    }

    private MockHttpSession authenticateUserWithLoginAndPassword(String login, String password) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(login, password);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext);

        return session;
    }

}
