package com.unnathy.fieldwise.errorreport;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "client_error_reports")
public class ClientErrorReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    private String name;

    @Column(columnDefinition = "text")
    private String stack;

    @Column(columnDefinition = "text")
    private String route;

    @Column(columnDefinition = "text")
    private String url;

    @Column(name = "user_agent", columnDefinition = "text")
    private String userAgent;

    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String email;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode context;
    // getters/setters...
}
