package com.Easton.motordesk_backend.service;

import com.Easton.motordesk_backend.dto.AppUserDto;

import java.util.List;

public interface AppUserService {

    AppUserDto createAppUser(AppUserDto appUserDto);

    AppUserDto getAppUserById(Long userId);

    List<AppUserDto> getAllAppUsers();

    AppUserDto updateAppUser(Long id, AppUserDto updatedAppUser);

    void deleteAppUser(Long id);
}