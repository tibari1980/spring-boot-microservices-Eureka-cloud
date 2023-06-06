package com.arcesi.identityservice.security.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {


    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    public String get() {
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	
        return "GET:: management controller";
    }
    
    @GetMapping("/profil")
    public Authentication profil() {
    	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
    	return auth;
    }
    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
