package com.example.postalitemsapplication.controller;

import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.service.ItemService;
import com.example.postalitemsapplication.utils.ItemUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.postalitemsapplication.model.TypeOfItem.LETTER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private ItemUtils itemUtils;

    @InjectMocks
    private ItemController itemController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void registerItemTest() throws Exception {
        Item item = new Item(2, "Иван", LETTER, "47000", "Москва, ул.Суворова, д.43, кв.15", "Зарегистрировано", null);
        String itemJson = objectMapper.writeValueAsString(item);

        Mockito.when(itemUtils.convertItemDtoToItem(any())).thenReturn(item);
        Mockito.when(itemService.save(any())).thenReturn(item);
        mockMvc.perform(MockMvcRequestBuilders.post("/items/register")
                .contentType(MediaType.APPLICATION_JSON).content(itemJson))
                .andExpect(jsonPath("$").value(2))
                .andExpect(status().isOk());

        verify(itemService, times(1)).save(item);
    }

    @Test
    public void deliveryItemTest() throws Exception {
        Item item = new Item(2, "Иван", LETTER, "47000", "Москва, ул.Суворова, д.43, кв.15", "Зарегистрировано", null);

        Mockito.when(itemService.getByID(ArgumentMatchers.anyInt())).thenReturn(item);
        mockMvc.perform(MockMvcRequestBuilders.post("/items/delivery/2"))
                .andExpect(status().isOk());

        verify(itemService, times(1)).getByID(ArgumentMatchers.anyInt());

        Assertions.assertEquals(item.getStatus(), "Отправление доставлено адресату: Иван");
    }
}
