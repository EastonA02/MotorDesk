package com.Easton.motordesk_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VehicleDto {

    private Long id;
    private String vin;
    private String plate;

    //map foreign keys to their id
    private Long customerId;
    private Long shopId;

}
