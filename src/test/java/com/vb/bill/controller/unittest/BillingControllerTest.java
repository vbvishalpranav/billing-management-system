package com.vb.bill.controller.unittest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vb.bill.controller.BillingController;
import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;
import com.vb.bill.service.BillingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(BillingController.class)
//@TestPropertySource("classpath:application-test.properties")
public class BillingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @MockitoBean
//    private ModelMapper modelMapper;

    @MockitoBean
    private BillingService billingService;

    private static BillingDTO billingDTO;

    @BeforeAll
    public static void setUp() {

       BillingDTO billingDTO = new BillingDTO();
        billingDTO.setbStreet("Chennai");
        billingDTO.setCity("triplicane");
        billingDTO.setName("Virat");
    }

    @Test
    public void createBillTest() throws Exception {
        Billing billing = new Billing();
        billing.setbStreet("Chennai");
        billing.setbCity("triplicane");
        billing.setbName("Rohit");

        // when(modelMapper.map(billingDTO, Billing.class)).thenReturn(billing);
        when(billingService.createBill(billingDTO)).thenReturn(billing);


        // Perform POST request to create a new bill and validate the response
        mockMvc.perform(post("/billing/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"bStreet\": \"Chennai\", \"bCity\": \"Chennai\", \"bName\": \"Rohit\" }"))  // JSON request body
                .andExpect(status().isOk())  // Expecting 201 Created
                .andExpect(jsonPath("$.bName").value("Rohit"));// Validate response fields
    }

//    @Test
//    public void getBillingByIdTest() throws Exception {
//
//        billingService.createBill(billingDTO);
//        ResultActions response = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .get("/billing/get/2"));
//
//        response.andExpect(status().isOk())
//                .andExpect(jsonPath("$.bName", Matchers.is("Virat")));
//    }
//
//    @Test
//    public void updateBillingDetailsTest() throws Exception {
//
//
//        billingService.createBill(billingDTO);
//        Billing updateBillingDTO = new Billing();
//        updateBillingDTO.setbStreet("Chennai");
//        updateBillingDTO.setbCity("triplicane");
//        updateBillingDTO.setbName("Rohit");
//        String jsonAsString = objectMapper.writeValueAsString(updateBillingDTO);
//
//        ResultActions response = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .put("/billing/update/2")
//                        .content(jsonAsString)
//                        .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(status().isOk())
//                .andExpect(jsonPath("$.bName", Matchers.is("Rohit")));
//    }
//
//    @Test
//    public void deleteBillingDetailsTest() throws Exception {
//
//        billingService.createBill(billingDTO);
//        ResultActions response = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .delete("/billing/delete/1"));
//
//        response.andExpect(status().isOk())
//                .andExpect(content().string("deletion is successfully"));
//
//    }
}
