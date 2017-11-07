package com.kilfat.integration;

import static com.kilfat.config.ServiceConstants.ACCOUNT_PATH;
import static com.kilfat.config.ServiceConstants.TRANSACTION_PATH;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.kilfat.config.TestsBase;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

public class FundsTransactionControllerTest extends TestsBase {

    @Test
    @WithMockUser(username = "User1",
        password = "password1",
        authorities = {"USER"})
    public void createTransaction() throws Exception {
        String json = "{\n"
                      + "\t\"accountType\": \"BANK_CARD\",\n"
                      + "\t\"amount\": \"1252\"\n"
                      + "}";
        String response = mockMvc.perform(post(TRANSACTION_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertNotNull(response);
        Integer integer = JsonPath.read(response, "$.id");
        mockMvc.perform(get(ACCOUNT_PATH + integer.longValue()))
            .andExpect(status().isOk()).andDo(print());
    }
}
