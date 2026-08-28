package com.Easton.motordesk_backend.mapper;

import com.Easton.motordesk_backend.dto.ShopDto;
import com.Easton.motordesk_backend.entity.Shop;

public class ShopMapper {

    //map shop entity to shop dto
    public static ShopDto mapToShopDto(Shop shop){
        return new ShopDto(
                shop.getId(),
                shop.getName(),
                shop.getAddress(),
                shop.getPhone()
        );
    }

    //map shop dto to entity
    public static Shop mapToShop(ShopDto shopDto){
        return new Shop(
                shopDto.getId(),
                shopDto.getName(),
                shopDto.getAddress(),
                shopDto.getPhone()
        );
    }
}
