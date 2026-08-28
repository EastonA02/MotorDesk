package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.ShopDto;
import com.Easton.motordesk_backend.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController //Mark this class to handle HTTP req's
@RequestMapping("/api/motordesk") //define base URL

public class ShopController {

    //Inject Shop service to use route request to methods
    private ShopService shopService;

    //map POST request to create method
    @PostMapping
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto){
        ShopDto newShop = shopService.createShop(shopDto);

        //create shop & return status code 201 - "created"
        return new ResponseEntity<>(newShop, HttpStatus.CREATED);
    }

    //map GET request with id to get method
    @GetMapping("{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable("id") long shopId){
        ShopDto shopDto = shopService.getShopById(shopId);

        //return shop & status code 200 - "ok"
        return ResponseEntity.ok(shopDto);
    }

    //map GET request get all method
    @GetMapping
    ResponseEntity<List<ShopDto>> getAllShops(){
        List<ShopDto> shops = shopService.getAllShops();

        //return list of shops & status code 200 - "ok"
        return ResponseEntity.ok(shops);
    }

    //map POST request with id to update method
    @PutMapping("{id}")
    public ResponseEntity<ShopDto> updateShop(
            //id from url
            @PathVariable("id") Long shopId,
            //ShopDto from request body
            @RequestBody ShopDto shopDto
    ) {
        ShopDto updatedShop = shopService.updateShop(shopId, shopDto);

        //update shop & return status code 200 - "ok"
        return ResponseEntity.ok(updatedShop);
    }

    //map DELETE request with id to delete method
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable("id") Long shopId){
        shopService.deleteShop(shopId);

        //return status code 204 - "no content"
        return ResponseEntity.noContent().build();
    }

}
