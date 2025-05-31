package com.codeofraj.zoomverse.repository;

import com.codeofraj.zoomverse.exception.EmailAlreadyExistsException;
import com.codeofraj.zoomverse.exception.UserCreationException;
import com.codeofraj.zoomverse.exception.UserIdNotFoundException;
import com.codeofraj.zoomverse.model.Session;
import com.codeofraj.zoomverse.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    private static final String COLLECTION_NAME = "users";
    private static  final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    Firestore db = FirestoreClient.getFirestore();
    public UserRepository(Firestore db) {
        this.db = db;
    }

    public User createUser(String email, String password, String username)
            throws ExecutionException, InterruptedException {
        
        logger.info("inside user repository createUser() method");

        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME)
                                                    .whereEqualTo("email", email).get()
                                                        .get().getDocuments();

        if (!documents.isEmpty()) {
            logger.info("User with email '{}' already exist.", email);
            throw new EmailAlreadyExistsException(email);
        }

        logger.info(" Creating User with email '{}'", email);

        String userId = UUID.randomUUID().toString();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(userId);

        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUsername(username);

        Session newSession = new Session();
        newSession.setIsOnline(true);
        newSession.setLastActive(LocalDateTime.now().toString());

        newUser.setSession(newSession);

        try {
            ApiFuture<WriteResult> result = docRef.set(newUser);
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw new UserCreationException(e.getMessage());
        }

        newUser.setPassword(newUser.getPassword().replaceAll(".", "*"));

        logger.info("User created successfully.");
        return newUser;
    }

    public User findUserByUserId(String userId)
            throws ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME)
                                                    .whereEqualTo("userId", userId).get()
                                                        .get().getDocuments();
        if (documents.isEmpty()) {
            logger.error("User with id '{}' do not exists", userId);
            throw new UserIdNotFoundException(userId);
        }

        logger.info("User with id '{}' found", userId);
        return documents.getFirst().toObject(User.class);
    }
}