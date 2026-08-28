package com.Easton.motordesk_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

    public class CustomerDto {
        private Long id;
        private String phone;
        private String address;
        private String name;
        /*
        Dto should never reference an entity
        eg. private Shop shop;
        "flatten" to just ID
        */
        private Long shopId;
    }
