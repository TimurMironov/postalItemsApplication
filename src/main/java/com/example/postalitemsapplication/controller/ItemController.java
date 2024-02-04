package com.example.postalitemsapplication.controller;

import com.example.postalitemsapplication.exception.ItemServiceException;
import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.model.dto.ItemRequestDTO;
import com.example.postalitemsapplication.model.dto.ItemResponseDTO;
import com.example.postalitemsapplication.service.ItemService;
import com.example.postalitemsapplication.utils.CommonUtils;
import com.example.postalitemsapplication.utils.ItemUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/items")
@Tag(name = "Почтовые отправления", description = "Контроллер для работы с почтовыми отправлениями")
public class ItemController {

    private final ItemService itemService;

    private final ItemUtils itemUtils;

    private final CommonUtils commonUtils;

    @Autowired
    public ItemController(ItemService itemService, ItemUtils itemUtils, CommonUtils commonUtils) {
        this.itemService = itemService;
        this.itemUtils = itemUtils;
        this.commonUtils = commonUtils;
    }

    @Operation(summary = "Регистрация почтового отправления")
    @PostMapping("/")
    public ResponseEntity<Integer> registerItem(@Valid @RequestBody ItemRequestDTO itemDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String exceptionMessage = commonUtils.createExceptionMessage(bindingResult);
            throw new ItemServiceException(exceptionMessage);
        } else {
            Item registerItem = itemUtils.convertItemDtoToItem(itemDTO);
            Integer savedItemID = itemService.save(registerItem).getId();
            return new ResponseEntity<>(savedItemID, HttpStatus.OK);
        }
    }

    @Operation(summary = "Изменение статуса почтового отправления при доставке")
    @PostMapping("/delivery/{id}")
    public ResponseEntity<HttpStatus> deliveryItem(@Parameter(description = "ID почтового отправления")
                                                   @PathVariable(name = "id") Integer itemID) {

        Item item = itemService.getByID(itemID);
        item.setStatus("Отправление доставлено адресату: " + item.getNameOfRecipient());
        itemService.save(item);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Получение информации о почтовом отправлении и его передвижении")
    @GetMapping("/{id}")
    public ItemResponseDTO getItemInfo(@Parameter(description = "ID почтового отправления")
                                   @PathVariable(name = "id") Integer itemId) {
        Item item = itemService.getByID(itemId);
        return itemUtils.convertItemToItemDTO(item);
    }

    @GetMapping("/")
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
        List<ItemResponseDTO> list = itemService.list()
                .stream()
                .map((itemUtils::convertItemToItemDTO))
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable(name = "id") Integer id){
        itemService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
