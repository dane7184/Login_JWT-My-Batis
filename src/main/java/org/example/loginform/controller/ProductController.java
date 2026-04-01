package org.example.loginform.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.loginform.model.entity.AppUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    @GetMapping
    public String getAllProducts() {

        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = appUser.getUserId();
        System.out.println("userId = " + userId);

        return appUser.getUsername();
    }
}
