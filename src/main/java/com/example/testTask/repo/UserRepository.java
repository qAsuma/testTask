package com.example.testTask.repo;

import com.example.testTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByBirthDateBetween(LocalDate birthDate, LocalDate birthDate2);

}
