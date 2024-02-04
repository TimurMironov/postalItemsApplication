package com.example.postalitemsapplication.utils;

import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.model.dto.ItemRequestDTO;
import com.example.postalitemsapplication.model.dto.ItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemUtils {

    private final PostOfficeUtils postOfficeUtils;

    @Autowired
    public ItemUtils(PostOfficeUtils postOfficeUtils) {
        this.postOfficeUtils = postOfficeUtils;
    }

    public ItemResponseDTO convertItemToItemDTO(Item item){
        return ItemResponseDTO.builder().nameOfRecipient(item.getNameOfRecipient())
                .typeOfItem(item.getTypeOfItem())
                .indexOfRecipient(item.getIndexOfRecipient())
                .addressOfRecipient(item.getAddressOfRecipient())
                .status(item.getStatus())
                .postOffices(item.getPostOffices()
                        .stream()
                        .map(postOfficeUtils::convertPostOfficeToPostOfficeDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Item convertItemDtoToItem(ItemRequestDTO itemDTO){
        return Item.builder().nameOfRecipient(itemDTO.getNameOfRecipient())
                .typeOfItem(itemDTO.getTypeOfItem())
                .indexOfRecipient(itemDTO.getIndexOfRecipient())
                .addressOfRecipient(itemDTO.getAddressOfRecipient())
                .status(itemDTO.getStatus()).build();
    }
}
