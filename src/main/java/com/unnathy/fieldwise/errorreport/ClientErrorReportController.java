package com.unnathy.fieldwise.errorreport;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/client-errors")
public class ClientErrorReportController {
    private final ClientErrorReportRepository repo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClientErrorReportController(ClientErrorReportRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClientErrorReportRequest req) {
        ClientErrorReport entity = new ClientErrorReport();
        entity.setMessage(req.message);
        entity.setName(req.name);
        entity.setStack(req.stack);
        entity.setRoute(req.route);
        entity.setUrl(req.url);
        entity.setUserAgent(req.userAgent);
        if (req.user != null) {
            entity.setUserId(req.user.userId);
            entity.setUsername(req.user.username);
            entity.setEmail(req.user.email);
        }
        if (req.context != null) {
            entity.setContext(objectMapper.valueToTree(req.context));
        }
        // Optional: if you want to keep client timestamp
        if (req.timestamp != null) {
            entity.setCreatedAt(Instant.parse(req.timestamp));
        }
        repo.save(entity);
        return ResponseEntity.accepted().build();
    }
}
