package com.Easton.motordesk_backend.mapper;

import com.Easton.motordesk_backend.dto.AppUserDto;
import com.Easton.motordesk_backend.entity.AppUser;
import com.Easton.motordesk_backend.entity.Shop;

public class AppUserMapper {
    //map AppUser entity to AppUserDto
    public static AppUserDto mapToAppUserDto(AppUser appUser){
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(appUser.getId());
        appUserDto.setName(appUser.getName());
        appUserDto.setEmail(appUser.getEmail());
        appUserDto.setRole(appUser.getRole());
        appUserDto.setShopId(appUser.getShop().getId());
        //password left out intentionally to avoid being exposed
        return appUserDto;
    }

    //map AppUserDto to AppUser entity
    public static AppUser mapToAppUser (AppUserDto appUserDto, Shop shop){
        return new AppUser(
                //constructor expects id, shop, name, email, password, role
                appUserDto.getId(),
                shop,
                appUserDto.getName(),
                appUserDto.getEmail(),
                appUserDto.getPassword(),
                appUserDto.getRole()
        );
    }
}
