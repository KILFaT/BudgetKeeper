package com.kilfat.integration;

import static com.kilfat.config.ServiceConstants.USER_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

public class UserControllerTest extends TestsBase {

    @Test
    @WithMockUser(username = "admin",
        password = "password123",
        authorities = {"ADMIN"})
    public void createAndGetUser() throws Exception {
        String json = "{\n"
                      + "\t\"username\": \"Vasya1\",\n"
                      + "\t\"password\": \"password123\",\n"
                      + "\t\"email\": \"asd@mail.ru\",\n"
                      + "\t\"userRole\": [{\n"
                      + "\t\t\"role\": \"USER\"\n"
                      + "\t}]\n"
                      + "}";
        mockMvc.perform(get(USER_PATH + "/User1"))
            .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(post(USER_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated());
        mockMvc.perform(get("/api/"))
            .andExpect(status().isOk());
    }
}
