package com.kilfat.config;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@ActiveProfiles(DataConfigProfile.HSQLDB)
@ContextConfiguration(classes = {HSQLConfig.class, WebConfig.class, TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestsBase {

    protected static MockMvc mockMvc;
    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity())
            .build();
    }
}
