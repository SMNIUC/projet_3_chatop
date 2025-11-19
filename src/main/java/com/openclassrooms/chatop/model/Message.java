package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
