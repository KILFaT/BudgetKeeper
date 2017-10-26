package com.kilfat.integration;

import com.kilfat.config.DataConfigProfile;
import com.kilfat.config.HSQLConfig;
import com.kilfat.config.TestConfig;
import com.kilfat.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(DataConfigProfile.HSQLDB)
@ContextConfiguration(classes = {HSQLConfig.class, TestConfig.class, WebConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DatabaseTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void simpleTest() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk());
    }
}
