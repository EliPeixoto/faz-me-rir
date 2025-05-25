package br.com.fazmerir.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String action;
    private String entity;
    private Long entityId;
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    private String details;

}
