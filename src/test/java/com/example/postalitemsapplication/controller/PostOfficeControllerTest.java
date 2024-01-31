package com.example.postalitemsapplication.controller;

import com.example.postalitemsapplication.model.PostOffice;
import com.example.postalitemsapplication.service.PostOfficeService;
import com.example.postalitemsapplication.utils.PostOfficeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PostOfficeControllerTest {

    @Mock
    private PostOfficeService postOfficeService;

    @Mock
    private PostOfficeUtils postOfficeUtils;

    @InjectMocks
    private PostOfficeController postOfficeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postOfficeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void registerPostOfficeTest() throws Exception {
        PostOffice postOffice = new PostOffice(2, "460000", "Городское почтовое отделение", "Москва, ул.Суворова, д.45", null);
        String itemJson = objectMapper.writeValueAsString(postOffice);

        Mockito.when(postOfficeUtils.convertPostOfficeDtoToPostOffice(any())).thenReturn(postOffice);
        Mockito.when(postOfficeService.save(any())).thenReturn(postOffice);
        mockMvc.perform(MockMvcRequestBuilders.post("/post-offices/register")
                        .contentType(MediaType.APPLICATION_JSON).content(itemJson))
                .andExpect(jsonPath("$").value(2))
                .andExpect(status().isOk());
    }
}
