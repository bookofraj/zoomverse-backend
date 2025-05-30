package com.codeofraj.zoomverse.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import com.google.auth.oauth2.GoogleCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FirebaseConfig {

    @Value("${FIREBASE_PRIVATE_KEY_ID:#{null}}")
    private String privateKeyId;

    @Value("${FIREBASE_PRIVATE_KEY:#{null}}")
    private String privateKey;

    @Value("${FIREBASE_CLIENT_ID:#{null}}")
    private String clientId;

    @Value("${SERVICE_ACCOUNT_PATH}")
    private String serviceAccountPath;

    private FirebaseApp initializeFirebaseApp() throws IOException {
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        // Read the base service account file
        Resource resource = new ClassPathResource(serviceAccountPath.replace("src/main/resources/", ""));
        JSONObject serviceAccount = new JSONObject(new String(resource.getInputStream().readAllBytes()));

        // Override sensitive fields with environment variables if provided
        if (privateKeyId != null) {
            serviceAccount.put("private_key_id", privateKeyId);
        }
        if (privateKey != null) {
            serviceAccount.put("private_key", privateKey);
        }
        if (clientId != null) {
            serviceAccount.put("client_id", clientId);
        }

        // Convert to InputStream
        InputStream serviceAccountStream = new ByteArrayInputStream(serviceAccount.toString().getBytes());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public Firestore firestore() throws IOException {
        initializeFirebaseApp();
        return FirestoreClient.getFirestore();
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        initializeFirebaseApp();
        return FirebaseAuth.getInstance();
    }
}
