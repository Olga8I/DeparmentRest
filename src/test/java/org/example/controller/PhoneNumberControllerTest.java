package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.PhoneNumberController;
import org.example.dto.PhoneNumberDto;
import org.example.service.PhoneNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PhoneNumberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhoneNumberService phoneNumberService;

    @InjectMocks
    private PhoneNumberController phoneNumberController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController).build();
    }

    @Test
    public void testCreatePhoneNumber() throws Exception {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto("123456789", null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/phoneNumbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phoneNumberDto)))
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        verify(phoneNumberService, times(1)).save(any(PhoneNumberDto.class));
    }

    @Test
    public void testUpdatePhoneNumber() throws Exception {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto("987654321", null);
        phoneNumberDto.setId(1L);
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/phoneNumbers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phoneNumberDto)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        verify(phoneNumberService, times(1)).update(any(PhoneNumberDto.class));
    }

    @Test
    public void testGetPhoneNumberById() throws Exception {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto("123456789", null);
        phoneNumberDto.setId(1L);

        when(phoneNumberService.findById(1L)).thenReturn(phoneNumberDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/phoneNumbers/{id}", 1L))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(phoneNumberDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllPhoneNumbers() throws Exception {
        PhoneNumberDto phoneNumberDto1 = new PhoneNumberDto("123456789", null);
        PhoneNumberDto phoneNumberDto2 = new PhoneNumberDto("987654321", null);
        List<PhoneNumberDto> phoneNumberDtos = List.of(phoneNumberDto1, phoneNumberDto2);

        when(phoneNumberService.findAll()).thenReturn(phoneNumberDtos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/phoneNumbers"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(phoneNumberDtos), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDeletePhoneNumber() throws Exception {
        doNothing().when(phoneNumberService).delete(1L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/phoneNumbers/{id}", 1L))
                .andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
        verify(phoneNumberService, times(1)).delete(1L);
    }
}

