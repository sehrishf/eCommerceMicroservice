package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private  Long nextId=1L;

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {

        UserResponse user = userService.fetchUser(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.fetchAllUsers());
    }


    @PostMapping("/api/user")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
             userService.addUser(userRequest);
        return ResponseEntity.ok ("User added");

    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {
        userService.updateUser(id, updatedUserRequest);
        return ResponseEntity.ok("User updated");
    }

    @PatchMapping("/api/user/{id}")
    public  ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {

        userService.patchUser(id, updatedUserRequest);
        return ResponseEntity.ok("User updated");
    }


}
