package com.example.LibraryManagementSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String userName;
    private String userSurname;
}
