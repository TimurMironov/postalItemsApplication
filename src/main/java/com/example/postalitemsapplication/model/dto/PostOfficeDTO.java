package com.example.postalitemsapplication.model.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Почтовое отделение")
public class PostOfficeDTO {

    @Schema(description = "Индекс почтового отделения")
    @NotEmpty(message = "Индекс почтового отделения не должен быть пустым")
    private String index;

    @Schema(description = "Наименование почтового отделения")
    @NotEmpty(message = "Наименование почтового отделения не должно быть пустым")
    private String name;

    @Schema(description = "Адрес почтового отделения")
    @NotEmpty(message = "Адрес почтового отделения не должно быть пустым")
    private String address;


}
