package com.codeofraj.zoomverse.service;

import com.codeofraj.zoomverse.dto.ApiResponse;
import com.codeofraj.zoomverse.exception.EmailAlreadyExistsException;
import com.codeofraj.zoomverse.exception.UserIdNotFoundException;
import com.codeofraj.zoomverse.model.Session;
import com.codeofraj.zoomverse.model.User;
import com.codeofraj.zoomverse.repository.UserRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<ApiResponse<User>> registerUser(String email, String password, String username)
            throws ExecutionException, InterruptedException {

        logger.info("inside user service registerUser() method");

        try {
            User newUser = userRepository.createUser(email, password, username);
            logger.info("User created: email: {}, userId: {}", newUser.getEmail(), newUser.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(newUser, "User created successfully"));
        } catch (EmailAlreadyExistsException e) {
            logger.error("Email already exists: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("Email Already Taken"));
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Internal server error"));
        }
    }

    public ResponseEntity<ApiResponse<User>> getUserByUserId(String userId) throws ExecutionException, InterruptedException {
        logger.info("inside user controller getUser() by user id method");

        try {
            User foundUser = userRepository.findUserByUserId(userId);
            logger.info("User found: email: {}, userId: {}", foundUser.getEmail(), foundUser.getUserId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success(foundUser, "User found successfully"));
        } catch (UserIdNotFoundException e) {
            logger.error("User with id '{}' not found", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("User not found"));
        }
    }
}