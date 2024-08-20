package org.example.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.PhoneNumberCreateDto;
import org.example.dto.PhoneNumberResponseDto;
import org.example.dto.PhoneNumberUpdateDto;
import org.example.service.PhoneNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PhoneNumberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhoneNumberService phoneNumberService;

    @InjectMocks
    private PhoneNumberController phoneNumberController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController).build();
    }

    @Test
    public void testCreatePhoneNumber() throws Exception {
        PhoneNumberCreateDto phoneNumberCreateDto = new PhoneNumberCreateDto("123456789");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/phoneNumbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phoneNumberCreateDto)))
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        verify(phoneNumberService, times(1)).save(any(PhoneNumberCreateDto.class));
    }

    @Test
    public void testUpdatePhoneNumber() throws Exception {
        PhoneNumberUpdateDto phoneNumberUpdateDto = new PhoneNumberUpdateDto(1L, "987654321");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/phoneNumbers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phoneNumberUpdateDto)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        verify(phoneNumberService, times(1)).update(any(PhoneNumberUpdateDto.class));
    }

    @Test
    public void testGetPhoneNumberById() throws Exception {
        PhoneNumberResponseDto phoneNumberResponseDto = new PhoneNumberResponseDto("123456789", null);
        phoneNumberResponseDto.setId(1L);

        when(phoneNumberService.findById(1L)).thenReturn(phoneNumberResponseDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/phoneNumbers/{id}", 1L))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(phoneNumberResponseDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllPhoneNumbers() throws Exception {
        PhoneNumberResponseDto phoneNumberResponseDto1 = new PhoneNumberResponseDto("123456789", null);
        PhoneNumberResponseDto phoneNumberResponseDto2 = new PhoneNumberResponseDto("987654321", null);
        List<PhoneNumberResponseDto> phoneNumberResponseDtos = List.of(phoneNumberResponseDto1, phoneNumberResponseDto2);

        when(phoneNumberService.findAll()).thenReturn(phoneNumberResponseDtos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/phoneNumbers"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(phoneNumberResponseDtos), mvcResult.getResponse().getContentAsString());
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
