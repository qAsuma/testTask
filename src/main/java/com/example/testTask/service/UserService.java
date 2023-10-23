package com.example.testTask.service;

import com.example.testTask.entity.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User updatedUser);


    List<User> updateAllUsers(User updatedUser);


    void deleteUser(Long userId);

    List<User> findUsersByBirthDateRange(LocalDate fromDate, LocalDate toDate);
}
