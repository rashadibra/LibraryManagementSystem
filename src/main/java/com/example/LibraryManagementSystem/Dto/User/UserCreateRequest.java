package com.example.LibraryManagementSystem.Dto.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    @NotBlank(message = "İstifadəçinin adını qeyd edin")
    private String userName;
    @NotBlank(message = "İstifadəçinin soyadını qeyd edin")
    private String userSurname;
}
