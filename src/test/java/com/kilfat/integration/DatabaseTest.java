package com.kilfat.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kilfat.config.ServiceConstants;
import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class DatabaseTest extends TestsBase {

    @Test
    @WithMockUser(username = "admin",
        password = "password123",
        authorities = {"ADMIN"})
    public void simpleTest() throws Exception {
        mockMvc.perform(get(ServiceConstants.API_URL))
            .andExpect(status().isOk());
    }
}
