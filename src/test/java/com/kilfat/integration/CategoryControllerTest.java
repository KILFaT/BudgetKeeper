package com.kilfat.integration;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.kilfat.config.ServiceConstants.CATEGORY_PATH;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kilfat.config.TestsBase;
import com.kilfat.database.entity.User;
import com.kilfat.database.service.implementations.UserDetailServiceImpl;
import com.kilfat.web.model.UserPrincipal;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithUserDetails;

public class CategoryControllerTest extends TestsBase {

    @Test//userDetailServiceImpl
//    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailServiceImpl")
//    @WithMockUser(username = "User1",     password = "password1",     authorities = {"USER"})
    public void createAccountCategory() throws Exception {

        String json = "{\n"
                      + "\t\"name\": \"SomeCategory1\",\n"
                      + "\t\"userName\": \"User5\"\n"
                      + "}";
        mockMvc.perform(post(CATEGORY_PATH)//.with(user("admin"))
                            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated());
        mockMvc.perform(get(CATEGORY_PATH + "names"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.categories", hasSize(1)))
            .andExpect(jsonPath("$.categories[0].name").value("SomeCategory1"));
    }
}
