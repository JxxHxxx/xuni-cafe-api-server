package com.xuni.cafe.api;

import com.xuni.cafe.api.common.response.BasicResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/check-health")
    public ResponseEntity<BasicResponseBody> checkHealth() {
        return ResponseEntity.ok(new BasicResponseBody(200, "ok"));
    }
}
