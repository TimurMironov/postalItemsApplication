package com.example.postalitemsapplication.utils;

import com.example.postalitemsapplication.model.PostOffice;
import com.example.postalitemsapplication.model.dto.PostOfficeDTO;
import org.springframework.stereotype.Component;

@Component
public class PostOfficeUtils {

    public PostOffice convertPostOfficeDtoToPostOffice(PostOfficeDTO postOfficeDTO){
        return PostOffice.builder().index(postOfficeDTO.getIndex())
                .name(postOfficeDTO.getName())
                .address(postOfficeDTO.getAddress())
                .build();

    }

    public PostOfficeDTO convertPostOfficeToPostOfficeDto(PostOffice postOffice){
        return PostOfficeDTO.builder().index(postOffice.getIndex())
                .name(postOffice.getName())
                .address(postOffice.getAddress())
                .build();
    }


}
