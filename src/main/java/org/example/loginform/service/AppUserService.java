package org.example.loginform.service;

import org.example.loginform.model.request.AppUserRequest;
import org.example.loginform.model.response.AppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse register(AppUserRequest request);
}
