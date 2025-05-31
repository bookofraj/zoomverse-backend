package com.codeofraj.zoomverse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.concurrent.ExecutionException;
import com.codeofraj.zoomverse.model.DZI;
import com.codeofraj.zoomverse.dto.ApiResponse;
import com.codeofraj.zoomverse.service.DziService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/dzi")
public class DziController {

    private final DziService dziService;

    private static final Logger logger = LoggerFactory.getLogger(DziController.class);

    public DziController(DziService dziService){
        this.dziService = dziService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<DZI>> registerDZI(@RequestBody DZI dzi)
            throws ExecutionException, InterruptedException {
        logger.info("inside dzi controller registerDZI() method");
        return dziService.registerDZI(dzi);
    }

    @GetMapping("/search/{dziId}")
    public ResponseEntity<ApiResponse<DZI>> getDZIById(@PathVariable String dziId)
            throws ExecutionException, InterruptedException {
        logger.info("inside dzi controller getDZIById() method");
        return dziService.getDZIById(dziId);
    }
}