package com.kilfat.integration;

import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class CategoryControllerTest extends TestsBase {
    @Test
    @WithMockUser(username = "User1",
        password = "password1",
        authorities = {"USER"})
    public void createAccount() throws Exception {
        String json = "{\n"
                      + "\t\"username\": \"Vasya1\",\n"
                      + "\t\"password\": \"password123\",\n"
                      + "\t\"email\": \"asd@mail.ru\",\n"
                      + "\t\"userRole\": [{\n"
                      + "\t\t\"role\": \"USER\"\n"
                      + "\t}]\n"
                      + "}";
//        mockMvc.perform(get(USER_PATH + "/User1"))
//            .andExpect(status().isOk()).andDo(print());
//        mockMvc.perform(post(USER_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
//            .andExpect(status().isCreated());
//        mockMvc.perform(get("/api/"))
//            .andExpect(status().isOk());
    }
}
