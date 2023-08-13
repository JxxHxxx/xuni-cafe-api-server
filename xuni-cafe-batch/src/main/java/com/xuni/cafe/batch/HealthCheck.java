package com.xuni.cafe.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheck {

    @GetMapping("/health-batch")
    public ResponseEntity<String> checkBatchServerHealth() {

        return ResponseEntity.status(200).body("batch-server-ok");
    }
}
