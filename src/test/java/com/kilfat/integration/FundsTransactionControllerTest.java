package com.kilfat.integration;

import static com.kilfat.config.ServiceConstants.ACCOUNT_PATH;
import static com.kilfat.config.ServiceConstants.TRANSACTION_PATH;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        mockMvc.perform(get(ACCOUNT_PATH + "1")).andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(1000))
            .andDo(print());
        String json = "{\n"
                      + "\t\"accountId\": 1,\n"
                      + "\t\"transactionType\": \"COSTS\",\n"
                      + "\t\"amount\": 100,\n"
                      + "\t\"date\": \"10-11-2017 14:00:25\",\n"
                      + "\t\"categoryId\": 1\n"
                      + "}";
        String response = mockMvc.perform(post(TRANSACTION_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertNotNull(response);
        Integer integer = JsonPath.read(response, "$.id");
        mockMvc.perform(get(TRANSACTION_PATH + integer.longValue()))
            .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get(ACCOUNT_PATH + "1")).andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(900))
            .andDo(print());
    }

    @Test
    @WithMockUser(username = "User1",
        password = "password1",
        authorities = {"USER"})
    public void deleteTransaction() throws Exception {
        mockMvc.perform(get(ACCOUNT_PATH + "2")).andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(1000))
            .andDo(print());
        String json = "{\n"
                      + "\t\"accountId\": 2,\n"
                      + "\t\"transactionType\": \"COSTS\",\n"
                      + "\t\"amount\": 600,\n"
                      + "\t\"date\": \"10-11-2017 14:00:25\",\n"
                      + "\t\"categoryId\": 1\n"
                      + "}";
        String response = mockMvc.perform(post(TRANSACTION_PATH).contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        assertNotNull(response);
        Integer integer = JsonPath.read(response, "$.id");
        mockMvc.perform(get(TRANSACTION_PATH + integer.longValue()))
            .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get(ACCOUNT_PATH + "2")).andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(400))
            .andDo(print());
        mockMvc.perform(delete(TRANSACTION_PATH + integer.longValue())).andExpect(status().isNoContent());
        mockMvc.perform(get(ACCOUNT_PATH + "2")).andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(1000))
            .andDo(print());
    }
}
