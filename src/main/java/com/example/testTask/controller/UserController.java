package com.example.testTask.controller;

import com.example.testTask.entity.User;
import com.example.testTask.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api")
public class UserController {

    UserService userService;




    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(new User(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getAddress(), user.getPhoneNumber()));

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @Valid @RequestBody User updatedUser) {

        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);

    }

    @PatchMapping("/updateAll")
    public ResponseEntity<List<User>> updateAllUsers(@Valid @RequestBody User updatedUser) {

        List<User> updatedUsers = userService.updateAllUsers(updatedUser);
        return ResponseEntity.ok(updatedUsers);

    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/birthDateRange")
    public List<User> getUsersByBirthDateRange(
            @RequestParam("fromDate") LocalDate fromDate,
            @RequestParam("toDate") LocalDate toDate
    ) {

        return userService.findUsersByBirthDateRange(fromDate, toDate);
    }


}
