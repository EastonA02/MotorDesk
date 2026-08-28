package com.Easton.motordesk_backend.service.impl;

import com.Easton.motordesk_backend.dto.ShopDto;
import com.Easton.motordesk_backend.entity.Shop;
import com.Easton.motordesk_backend.exception.ResourceNotFoundException;
import com.Easton.motordesk_backend.mapper.ShopMapper;
import com.Easton.motordesk_backend.repository.ShopRepository;
import com.Easton.motordesk_backend.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    //allow to do sql commands using methods
    private ShopRepository shopRepository;

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        //convert received Dto from req body to entity
        Shop shop = ShopMapper.mapToShop(shopDto);

        //create and save new shop entity to database
        Shop newShop = shopRepository.save(shop);

        //return new shop as Dto to client
        return ShopMapper.mapToShopDto(newShop);
    }

    @Override
    public ShopDto getShopById(long shopId) {

        //Find shop entity by Id in database
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Shop does not exist"));

        //return shop entity as Dto to client
        return ShopMapper.mapToShopDto(shop);
    }

    @Override
    public List<ShopDto> getAllShops() {

        //return list of all shops in db
        List <Shop> shops = shopRepository.findAll();

        return shops.stream().map((shop) -> ShopMapper
                        .mapToShopDto(shop)) // for EACH shop, run it through the Mapper (Entity → DTO)
                .collect(Collectors.toList()); // gather all the resulting DTOs back into a real List
    }

    @Override
    public ShopDto updateShop(Long shopId, ShopDto updatedShop) {

        //validate shop exists
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "shop not found"));

        //update fields
        shop.setAddress(updatedShop.getAddress());
        shop.setName(updatedShop.getName());
        shop.setPhone(updatedShop.getPhone());

        //save updates to db
        Shop updatedShopObj = shopRepository.save(shop);

        //return updated shop entity as dto
        return ShopMapper.mapToShopDto(updatedShopObj);
    }

    @Override
    public void deleteShop(Long shopId) {

        //validate shop exists by id
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "shop not found"));

        //delete shop
        shopRepository.deleteById(shopId);

    }
}
