package com.vb.bill.service;

import com.vb.bill.Repo.BillingRepo;
import com.vb.bill.dto.BillingDTO;
import com.vb.bill.entity.Billing;
import com.vb.bill.exception.InvalidFieldException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BillingServiceImplTest {

    @Mock
    private BillingRepo billingRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BillingServiceImpl billingService;

    @Test
    public void createBillTest() {
        /*Given section-1. we create the arguments which needs to be passed in an calling method
                        2. define the mockito object behaviour using mockito.when
         */
        Billing billing = new Billing();
        billing.setbStreet("marl");

        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setName("rohit");
        billingDTO.setCity("mumbai");
        billingDTO.setbStreet("marl");
        Mockito.when(modelMapper.map(billingDTO, Billing.class)).thenReturn(billing);
        Mockito.when(billingRepo.save(billing)).thenReturn(billing);
        //When section - we are calling original methodl
        billing = billingService.createBill(billingDTO);

        //Then section we will check our assertions
        assertEquals("rohit", billing.getbName());


    }

    @Test
    public void getBillingByIdTest() {
        //Given
        Long id = 1L;
        Billing billing = new Billing();
        billing.setbName("sachin");
        billing.setbStreet("marl");
        billing.setbCity("chennai");
        Mockito.when(billingRepo.findById(id)).thenReturn(Optional.of(billing));
        //When section - we are calling original methodl
        billing = billingService.getBillingById(id);

        //Then section we will check our assertions
        assertEquals("sachin", billing.getbName());
    }

    @Test
    public void getBillingByIdExceptionTest() {
        //Given
        Long id = 5678L;

        // when block is empty for all exception related test cases

        billingRepo.findById(id);
        // then
        InvalidFieldException returnException = Assertions.assertThrows(InvalidFieldException.class, () -> {
                    billingService.getBillingById(id);
                }
        );

        assertTrue(returnException.getMessage().contains("Billing not found for id"));
    }

    @Test
    public void updateBillingDetailsTest() {

        //Given
        Long id = 1L;
        Billing billing = new Billing();
        billing.setbName("sachin");
        billing.setbStreet("marl");
        billing.setbCity("chennai");
        Mockito.when(billingRepo.findById(id)).thenReturn(Optional.of(billing));
        Mockito.when(billingRepo.save(billing)).thenReturn(billing);
        //When section - we are calling original methodl
        billing = billingService.updateBillingDetails(id, billing);
        //Then section we will check our assertions
        assertEquals("sachin", billing.getbName());
    }

    @Test
    public void deleteBillingDetailsTest() {
        //Given Section
        Long id = 1L;
        Billing billing = new Billing();
        billing.setbName("sachin");
        billing.setbStreet("marl");
        billing.setbCity("chennai");
        Mockito.when(billingRepo.findById(id)).thenReturn(Optional.of(billing));
        Mockito.doNothing().when(billingRepo).deleteById(id);
        // When Section
        String msg = billingService.deleteBillingDetails(id);
        //Then section we will check our assertions
        assertEquals("deletion is successfully", msg);
    }

    @Test
    public void deleteBillingDetailsExceptionTest() {
        //Given Section
        Long id = 1L;
        // When Section
        InvalidFieldException returnException = Assertions.assertThrows(InvalidFieldException.class, () -> {
                    billingService.deleteBillingDetails(id);
                }
        );
        // then
        assertTrue(returnException.getMessage().contains("Billing not found for id"));
    }

}
