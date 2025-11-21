package com.example.LibraryManagementSystem.Dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateResponse {
    private int id;
    private String userName;
    private String userSurname;
}
