package com.example.postalitemsapplication.model.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Почтовое отделение")
public class PostOfficeDTO {

    @Schema(description = "Индекс почтового отделения")
    private String index;

    @Schema(description = "Наименование почтового отделения")
    private String name;

    @Schema(description = "Адрес почтового отделения")
    private String address;


}
