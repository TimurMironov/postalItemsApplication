package com.example.postalitemsapplication.model.dto;

import com.example.postalitemsapplication.model.TypeOfItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@Schema(description = "Почтовые отправления")
public class ItemResponseDTO {

    @Schema(description = "Имя получателя почтового отправления")
    private String nameOfRecipient;

    @Schema(description = "Тип почтового отправления")
    private TypeOfItem typeOfItem;

    @Schema(description = "Индекс получателя почтового отправления")
    private String indexOfRecipient;

    @Schema(description = "Адрес получателя почтового отправления")
    private String addressOfRecipient;

    @Schema(description = "Статус почтового отправления")
    private String status;

    @Schema(description = "Путь почтового отправления")
    private List<PostOfficeDTO> postOffices;

}
