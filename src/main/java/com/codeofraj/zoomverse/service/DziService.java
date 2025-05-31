package com.codeofraj.zoomverse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.codeofraj.zoomverse.repository.DziRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.codeofraj.zoomverse.model.DZI;
import com.codeofraj.zoomverse.dto.ApiResponse;
import java.util.concurrent.ExecutionException;
import com.codeofraj.zoomverse.exception.DziIdNotFoundException;

@Service
public class DziService {

    private static final String COLLECTION_NAME = "dzi";
    private static final Logger logger = LoggerFactory.getLogger(DziService.class);
    private final DziRepository dziRepository;

    public DziService(DziRepository dziRepository){
        this.dziRepository = dziRepository;
    }

    public ResponseEntity<ApiResponse<DZI>> registerDZI(DZI dzi)
            throws ExecutionException, InterruptedException {

        logger.info("inside dzi service registerDZI() method");

        try {
            DZI newDZI = dziRepository.createDZI(dzi);
            logger.info("DZI created: dziId: {}", newDZI.getDziId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(newDZI, "DZI created successfully"));
        } catch (Exception e) {
            logger.error("Error creating DZI: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to create DZI: " + e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse<DZI>> getDZIById(String dziId)
            throws ExecutionException, InterruptedException {
        logger.info("inside dzi service getDZIById() method");

        try {
            DZI foundDZI = dziRepository.getDZIById(dziId);
            logger.info("DZI found: dziId: {}", foundDZI.getDziId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success(foundDZI, "DZI found successfully"));
        } catch (DziIdNotFoundException e) {
            logger.error("DZI with id '{}' not found", dziId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("DZI not found"));
        }
    }
}
