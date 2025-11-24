package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
   public List<UserEntity> findByUserNameAndUserSurname(String userName, String userSurname);
   public Optional<UserEntity> findByIdAndUserNameAndUserSurname(int id, String userName, String userSurname);
}
