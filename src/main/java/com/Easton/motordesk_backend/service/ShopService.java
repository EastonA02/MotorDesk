package com.Easton.motordesk_backend.service;

import com.Easton.motordesk_backend.dto.ShopDto;

import java.util.List;

public interface ShopService {

    //create shop
    ShopDto createShop(ShopDto shopDto);

    //fetch shop by id
    ShopDto getShopById(long shopId);

    //Gives ordered collection of ShopDto's
    List<ShopDto> getAllShops();

    //update shop details
    ShopDto updateShop(Long shopId, ShopDto updatedShop);

    //delete shop
    void deleteShop(Long shopId);
}
