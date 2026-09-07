package com.Easton.motordesk_backend.security;

import com.Easton.motordesk_backend.entity.AppUser;
import com.Easton.motordesk_backend.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor

//mark class as Spring managed bean(object)
@Service
public class AppUserDetailsService implements UserDetailsService {
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found"));

        //now return User wrapped as a UserDetail??
        return new AppUserDetails(user);
    }
}