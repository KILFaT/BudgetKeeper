package com.kilfat.integration;

import com.kilfat.config.TestsBase;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DatabaseTest extends TestsBase {

    @Test
    public void simpleTest() throws Exception {
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk());
    }
}
