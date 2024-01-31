package com.example.postalitemsapplication.controller;

import com.example.postalitemsapplication.model.Item;
import com.example.postalitemsapplication.model.PostOffice;
import com.example.postalitemsapplication.model.dto.PostOfficeDTO;
import com.example.postalitemsapplication.service.ItemService;
import com.example.postalitemsapplication.service.PostOfficeService;
import com.example.postalitemsapplication.utils.PostOfficeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-offices")
@Tag(name = "Почтовые отделения", description = "Контроллер для работы с почтовыми отделениями")
public class PostOfficeController {

    private final PostOfficeService postOfficeService;

    private final PostOfficeUtils postOfficeUtils;

    private final ItemService itemService;

    @Autowired
    public PostOfficeController(PostOfficeService postOfficeService, PostOfficeUtils postOfficeUtils
            , ItemService itemService) {
        this.postOfficeService = postOfficeService;
        this.postOfficeUtils = postOfficeUtils;
        this.itemService = itemService;
    }

    @Operation(summary = "Регистрация почтового отделения")
    @PostMapping("register")
    public ResponseEntity<Integer> registerPostOffice(@RequestBody PostOfficeDTO postOfficeDTO) {
        PostOffice registerPostOffice = postOfficeUtils.convertPostOfficeDtoToPostOffice(postOfficeDTO);
        Integer savedPostOfficeID = postOfficeService.save(registerPostOffice).getId();
        return new ResponseEntity<>(savedPostOfficeID, HttpStatus.OK);
    }

    @Operation(summary = "Поступление почтового отправления, добавление в список, изменение статуса почтового отправления")
    @PostMapping("/{id}/accept-item")
    public ResponseEntity<HttpStatus> acceptItem(@Parameter(description = "ID почтового отделения")
                                                 @PathVariable(name = "id") Integer postOfficeID
            , @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ID поступившего почтового отправления")
                                                 @RequestBody Integer itemID) {

        Item item = itemService.getByID(itemID);
        PostOffice postOffice = postOfficeService.getByID(postOfficeID);

        postOffice.addNewItem(item);
        item.setStatus("Отправление прибыло в почтовое отделение: " + postOffice.getName()
                + " индекс: " + postOffice.getIndex()
                + " адрес: " + postOffice.getAddress());

        postOfficeService.save(postOffice);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Отгрузка почтового отправления, изменение статуса почтового отправления")
    @PostMapping("/{id}/release-item")
    public ResponseEntity<HttpStatus> releaseItem(@Parameter(description = "ID почтового отделения")
                                                  @PathVariable(name = "id") Integer postOfficeID
            , @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Индекс поступившего почтового отправления")
                                                  @RequestBody Integer itemID) {

        Item item = itemService.getByID(itemID);
        PostOffice postOffice = postOfficeService.getByID(postOfficeID);

        item.setStatus("Отправление покинуло почтовое отделение: " + postOffice.getName()
                + " индекс: " + postOffice.getIndex()
                + " адрес: " + postOffice.getAddress());

        itemService.save(item);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
