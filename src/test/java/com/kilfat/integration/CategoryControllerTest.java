package com.kilfat.integration;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.kilfat.config.ServiceConstants.CATEGORY_PATH;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.http.MediaType;

public class CategoryControllerTest extends TestsBase {

    @Test
    public void createAccountCategory() throws Exception {
        String json = "{\n"
                      + "\t\"name\": \"SomeCategory1\",\n"
                      + "\t\"userName\": \"User5\"\n"
                      + "}";
        mockMvc.perform(post(CATEGORY_PATH).with(user(getUser("User5")))
                            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated());
        mockMvc.perform(get(CATEGORY_PATH + "names").with(user(getUser("User5"))))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name").value("SomeCategory1"));
    }
}
