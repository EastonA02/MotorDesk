package com.Easton.motordesk_backend.service.impl;

import com.Easton.motordesk_backend.dto.AppUserDto;
import com.Easton.motordesk_backend.entity.AppUser;
import com.Easton.motordesk_backend.entity.Shop;
import com.Easton.motordesk_backend.exception.ResourceNotFoundException;
import com.Easton.motordesk_backend.mapper.AppUserMapper;
import com.Easton.motordesk_backend.repository.AppUserRepository;
import com.Easton.motordesk_backend.repository.ShopRepository;
import com.Easton.motordesk_backend.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private ShopRepository shopRepository;

    @Override
    public AppUserDto createAppUser(AppUserDto appUserDto) {
        Shop shop = shopRepository.findById(appUserDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        AppUser appUser = AppUserMapper.mapToAppUser(appUserDto, shop);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return AppUserMapper.mapToAppUserDto(savedAppUser);
    }

    @Override
    public AppUserDto getAppUserById(Long userId) {
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return AppUserMapper.mapToAppUserDto(appUser);
    }

    @Override
    public List<AppUserDto> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers.stream()
                .map(AppUserMapper::mapToAppUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppUserDto updateAppUser(Long id, AppUserDto updatedAppUser) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Shop shop = shopRepository.findById(updatedAppUser.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        appUser.setName(updatedAppUser.getName());
        appUser.setEmail(updatedAppUser.getEmail());
        appUser.setPassword(updatedAppUser.getPassword());
        appUser.setRole(updatedAppUser.getRole());
        appUser.setShop(shop);

        AppUser savedAppUser = appUserRepository.save(appUser);
        return AppUserMapper.mapToAppUserDto(savedAppUser);
    }

    @Override
    public void deleteAppUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        appUserRepository.delete(appUser);
    }
}