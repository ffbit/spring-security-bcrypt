package com.ffbit.bcrypt.security.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Configuration
public class MockFactory {
    @Resource
    private WebApplicationContext wac;

    @Resource
    private FilterChainProxy springSecurityFilter;

    @Bean
    @Scope("prototype")
    public MockMvc createMockMvc() {
        return webAppContextSetup(wac)
                .addFilters(springSecurityFilter)
                .alwaysDo(print()).build();
    }

}
