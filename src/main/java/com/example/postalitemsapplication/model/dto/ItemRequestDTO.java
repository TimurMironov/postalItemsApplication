package com.example.postalitemsapplication.model.dto;

import com.example.postalitemsapplication.model.TypeOfItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@Schema(description = "Почтовые отправления")
public class ItemRequestDTO {

    @Schema(description = "Имя получателя почтового отправления")
    @NotEmpty(message = "Имя получателя не должно быть пустым")
    private String nameOfRecipient;

    @Schema(description = "Тип почтового отправления")
    @NotEmpty(message = "Тип отправления не должно быть пустым")
    private TypeOfItem typeOfItem;

    @Schema(description = "Индекс получателя почтового отправления")
    @NotEmpty(message = "Индекс получателя не должен быть пустым")
    private String indexOfRecipient;

    @Schema(description = "Адрес получателя почтового отправления")
    @NotEmpty(message = "Адрес получателя не должен быть пустым")
    private String addressOfRecipient;

    @Schema(description = "Статус почтового отправления")
    private String status;
}
