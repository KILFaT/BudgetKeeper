package com.kilfat.config;

import com.kilfat.config.security.SecurityConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

@ActiveProfiles(DataConfigProfile.HSQLDB)
@ContextConfiguration(classes = {WebConfig.class, SecurityConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@TestExecutionListeners(listeners = WithSecurityContextTestExecutionListener.class)
public abstract class TestsBase {

    protected static MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private Filter springSecurityFilterChain;
    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(springSecurityFilterChain).build();
    }

    public UserDetails getUser(String username) {
        return userDetailsService.loadUserByUsername(username);
    }
}
