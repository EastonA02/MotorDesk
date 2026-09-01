package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.AppUserDto;
import com.Easton.motordesk_backend.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/motordesk/user")
public class AppUserController {

    private AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserDto> createAppUser(@RequestBody AppUserDto appUserDto){
        AppUserDto newAppUser = appUserService.createAppUser(appUserDto);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserDto> getAppUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllAppUsers(){
        return ResponseEntity.ok(appUserService.getAllAppUsers());
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserDto> updateAppUser(@PathVariable("id") Long id, @RequestBody AppUserDto appUserDto){
        return ResponseEntity.ok(appUserService.updateAppUser(id, appUserDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable("id") Long id){
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}