package com.vb.bill.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;
import com.vb.bill.service.BillingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application-test.properties")
public class BillingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BillingService billingService;

    private static BillingDTO billingDTO;

    @BeforeAll
    public static void setUp() {
        billingDTO = new BillingDTO();
        billingDTO.setbStreet("Chennai");
        billingDTO.setCity("triplicane");
        billingDTO.setName("Virat");
    }

    @Test
    public void createBillTest() throws Exception {
        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setbStreet("Chennai");
        billingDTO.setCity("triplicane");
        billingDTO.setName("dhoni");
        String jsonAsString = objectMapper.writeValueAsString(billingDTO);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/billing/create")
                        .content(jsonAsString)
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bName", Matchers.is("dhoni")));
    }

    @Test
    public void getBillingByIdTest() throws Exception {

        billingService.createBill(billingDTO);
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/billing/get/2"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bName", Matchers.is("Virat")));
    }

    @Test
    public void updateBillingDetailsTest() throws Exception {


        billingService.createBill(billingDTO);
        Billing updateBillingDTO = new Billing();
        updateBillingDTO.setbStreet("Chennai");
        updateBillingDTO.setbCity("triplicane");
        updateBillingDTO.setbName("Rohit");
        String jsonAsString = objectMapper.writeValueAsString(updateBillingDTO);

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/billing/update/2")
                        .content(jsonAsString)
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bName", Matchers.is("Rohit")));
    }

    @Test
    public void deleteBillingDetailsTest() throws Exception {

        billingService.createBill(billingDTO);
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/billing/delete/1"));

        response.andExpect(status().isOk())
                .andExpect(content().string("deletion is successfully"));

    }
}
