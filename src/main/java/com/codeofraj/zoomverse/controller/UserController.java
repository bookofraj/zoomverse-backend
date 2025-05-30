package com.codeofraj.zoomverse.controller;

import com.codeofraj.zoomverse.dto.ApiResponse;
import com.codeofraj.zoomverse.model.User;
import com.codeofraj.zoomverse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createNewUser(@RequestBody User user)
            throws ExecutionException, InterruptedException {
        logger.info("inside user controller createNewUser() method");
        return userService.registerUser(user.getEmail(), user.getPassword(), user.getUsername());
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String userId)
            throws ExecutionException, InterruptedException {
        logger.info("inside user controller getUser() method");
        return userService.getUserByUserId(userId);
    }
}
