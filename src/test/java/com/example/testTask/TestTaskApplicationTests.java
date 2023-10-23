package com.example.testTask;

import com.example.testTask.controller.UserController;
import com.example.testTask.entity.User;
import com.example.testTask.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"email\": \"test@example.com\"," +
                                " \"firstName\": \"yaroslav\"," +
                                " \"lastName\": \"deneha\"," +
                                "\"birthDate\": \"1994-11-22\"," +
                                "\"address\": \"someAddress\"," +
                                "\"phoneNumber\": \"0956814718\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {

        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setBirthDate(LocalDate.of(1990, 1, 1));
        newUser.setAddress("123 Main St");
        newUser.setPhoneNumber("555-1234");


        when(userService.createUser(any(User.class))).thenReturn(newUser);


        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"email\": \"test@example.com\"," +
                                " \"firstName\": \"John\"," +
                                " \"lastName\": \"Doe\"," +
                                "\"birthDate\": \"1990-01-01\"," +
                                "\"address\": \"123 Main St\"," +
                                "\"phoneNumber\": \"555-1234\"}"))
                .andExpect(status().isCreated());


        mockMvc.perform(put("/api/users/10")
                        .contentType("application/json")
                        .content("{\"email\": \"updated@example.com\"," +
                                " \"firstName\": \"UpdatedFirstName\"," +
                                " \"lastName\": \"UpdatedLastName\"," +
                                "\"birthDate\": \"1995-01-01\"," +
                                "\"address\": \"UpdatedAddress\"," +
                                "\"phoneNumber\": \"UpdatedPhoneNumber\"}"))
                .andExpect(status().isOk());

    }
    @Test
    void testUpdateAllUsers() throws Exception {

        User updatedUser = new User();
        updatedUser.setEmail("updated@example.com");

        List<User> updatedUsers = new ArrayList<>();
        updatedUsers.add(updatedUser);

        when(userService.updateAllUsers(any(User.class))).thenReturn(updatedUsers);

        mockMvc.perform(patch("/api/updateAll")
                        .contentType("application/json")
                        .content("{\"email\": \"updated@example.com\"" +

                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("updated@example.com"));
    }


    @Test
    void testDeleteUser() throws Exception {

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetUsersByBirthDateRange() throws Exception {

        mockMvc.perform(get("/api/birthDateRange")
                        .param("fromDate", "1990-01-01")
                        .param("toDate", "2000-12-31"))
                .andExpect(status().isOk());
    }

}
