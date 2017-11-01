package com.kilfat.integration;

import com.kilfat.config.ServiceConstants;
import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends TestsBase {
    private String USER_PATH = ServiceConstants.USER_PATH;

    @Test
    public void createAndGetUser() throws Exception {
        String json = "{\"name\":\"Vasya\",\"email\":\"asd@mail.ru\"}";
        mockMvc.perform(get(USER_PATH + "/1").with(user("admin").password("password123")))
                .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(post(USER_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/api/"))
                .andExpect(status().isOk());
    }
}
