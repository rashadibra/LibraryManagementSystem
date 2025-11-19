package com.example.LibraryManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userName", nullable = false, length = 100)
    private String userName;

    @Column(name = "userSurname", nullable = false, length = 100)
    private String userSurname;

}