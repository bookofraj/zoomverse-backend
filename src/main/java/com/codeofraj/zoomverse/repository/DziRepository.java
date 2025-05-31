package com.codeofraj.zoomverse.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import com.codeofraj.zoomverse.model.DZI;
import com.codeofraj.zoomverse.exception.ErrorRegisteringDzi;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import java.util.List;
import java.util.UUID;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFuture;
import com.codeofraj.zoomverse.exception.DziIdNotFoundException;
import org.springframework.http.ResponseEntity;
import com.codeofraj.zoomverse.dto.ApiResponse;

@Repository
public class DziRepository {

    private static final String COLLECTION_NAME = "dzi";
    private static final Logger logger = LoggerFactory.getLogger(DziRepository.class);

    Firestore db = FirestoreClient.getFirestore();
    public DziRepository(Firestore db) {
        this.db = db;
    }

    public DZI createDZI(DZI dzi)
            throws ExecutionException, InterruptedException {
        
        logger.info("inside dzi repository createDZI() method");

        String dziId = UUID.randomUUID().toString();
        
        logger.info("Creating DZI with dziId '{}'", dziId);

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(dziId);

        DZI newDZI = new DZI();
        newDZI.setDziId(dziId);
        newDZI.setDziName(dzi.getDziName());
        newDZI.setDziUrl(dzi.getDziUrl());
        newDZI.setOwnerId(dzi.getOwnerId());


        try {
            ApiFuture<WriteResult> result = docRef.set(newDZI);
        } catch (Exception e) {
            logger.error("Error creating DZI: {}", e.getMessage());
            throw new ErrorRegisteringDzi(e.getMessage());
        }

        logger.info("DZI registered successfully.");
        return newDZI;
    }

    public DZI getDZIById(String dziId)
            throws ExecutionException, InterruptedException {
        logger.info("inside dzi repository getDZIById() method");

        List<QueryDocumentSnapshot> documents = db.collection(COLLECTION_NAME)
                                                    .whereEqualTo("dziId", dziId).get()
                                                        .get().getDocuments();

        if (documents.isEmpty()) {
            logger.error("DZI with id '{}' do not exists", dziId);
            throw new DziIdNotFoundException(dziId);
        }

        logger.info("DZI with id '{}' found", dziId);
        return documents.getFirst().toObject(DZI.class);
    }
}
