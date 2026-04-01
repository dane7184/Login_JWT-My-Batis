package org.example.loginform.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.loginform.model.entity.AppUser;
import org.example.loginform.model.request.AppUserRequest;
import org.example.loginform.model.response.AppUserResponse;
import org.example.loginform.repository.AppUserRepository;
import org.example.loginform.service.AppUserService;
import org.jspecify.annotations.NullMarked;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NullMarked
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }

    @Override
    public AppUserResponse register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser = appUserRepository.register(request);
        for (String role : request.getRoles()){
            if (role.equals("ROLE_USER")){
                appUserRepository.insertUserIdAndRoleId(1L, appUser.getUserId());
            }
            if (role.equals("ROLE_ADMIN")){
                appUserRepository.insertUserIdAndRoleId(2L, appUser.getUserId());
            }
        }
        return modelMapper.map(appUserRepository.getUserById(appUser.getUserId()), AppUserResponse.class);
    }
}
