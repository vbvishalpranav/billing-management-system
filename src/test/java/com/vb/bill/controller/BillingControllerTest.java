package com.vb.bill.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vb.bill.dto.BillingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application-test.properties")
public class BillingControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createBillTest() throws Exception {
        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setbStreet("Chennai");
        billingDTO.setCity("triplicane");
        billingDTO.setName("Virat");
        String jsonAsString = objectMapper.writeValueAsString(billingDTO);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/billing/create")
                        .content(jsonAsString)
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bName", Matchers.is("Virat")));
    }

}
