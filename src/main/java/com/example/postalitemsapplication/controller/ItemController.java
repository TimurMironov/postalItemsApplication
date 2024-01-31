package com.example.postalitemsapplication.controller;

import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.model.dto.ItemDTO;
import com.example.postalitemsapplication.service.ItemService;
import com.example.postalitemsapplication.utils.ItemUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/items")
@Tag(name = "Почтовые отправления", description = "Контроллер для работы с почтовыми отправлениями")
public class ItemController {

    private final ItemService itemService;

    private final ItemUtils itemUtils;

    @Autowired
    public ItemController(ItemService itemService, ItemUtils itemUtils) {
        this.itemService = itemService;
        this.itemUtils = itemUtils;
    }

    @Operation(summary = "Регистрация почтового отправления")
    @PostMapping("/register")
    public ResponseEntity<Integer> registerItem(@RequestBody ItemDTO itemDTO) {
        Item registerItem = itemUtils.convertItemDtoToItem(itemDTO);
        Integer savedItemID = itemService.save(registerItem).getId();
        return new ResponseEntity<>(savedItemID, HttpStatus.OK);
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
    public ItemDTO getItemInfo(@Parameter(description = "ID почтового отправления")
                                   @PathVariable(name = "id") Integer itemId) {

        Item item = itemService.getByID(itemId);
        return itemUtils.convertItemToItemDTO(item);
    }
}
