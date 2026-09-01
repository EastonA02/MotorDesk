package com.Easton.motordesk_backend.dto;

import com.Easton.motordesk_backend.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AppUserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long shopId;
    private AppUser.UserRole role; //enum
}
