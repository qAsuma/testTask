package com.example.testTask.service;

import com.example.testTask.entity.User;
import com.example.testTask.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setLastName(updatedUser.getLastName());
            userToUpdate.setBirthDate(updatedUser.getBirthDate());
            userToUpdate.setAddress(updatedUser.getAddress());
            userToUpdate.setPhoneNumber(updatedUser.getPhoneNumber());


            return userRepository.save(userToUpdate);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    @Override
    public List<User> updateAllUsers(User updatedUser) {

        List<User> allUsers = userRepository.findAll();


        for (User user : allUsers) {
            user.setEmail(updatedUser.getEmail());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setBirthDate(updatedUser.getBirthDate());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }


        userRepository.saveAll(allUsers);

        return allUsers;
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);

    }

    @Override
    public List<User> findUsersByBirthDateRange(LocalDate fromDate, LocalDate toDate) {
        return userRepository.findByBirthDateBetween(fromDate, toDate);
    }
}
