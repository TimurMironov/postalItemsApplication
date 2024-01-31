package com.example.postalitemsapplication.utils;

import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.model.dto.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItemUtils {

    private final PostOfficeUtils postOfficeUtils;

    public ItemUtils(PostOfficeUtils postOfficeUtils) {
        this.postOfficeUtils = postOfficeUtils;
    }

    public ItemDTO convertItemToItemDTO(Item item){
        return ItemDTO.builder().nameOfRecipient(item.getNameOfRecipient())
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

    public Item convertItemDtoToItem(ItemDTO itemDTO){
        return Item.builder().nameOfRecipient(itemDTO.getNameOfRecipient())
                .typeOfItem(itemDTO.getTypeOfItem())
                .indexOfRecipient(itemDTO.getIndexOfRecipient())
                .addressOfRecipient(itemDTO.getAddressOfRecipient())
                .status(itemDTO.getStatus()).build();
    }
}
