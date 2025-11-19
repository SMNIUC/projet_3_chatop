package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;

    @Column(name = "name")
    private String rentalName;

    // TODO confirm data format
    @Column(name = "surface")
    private float rentalSurface;

    @Column(name = "price")
    private float rentalPrice;

    @Column(name = "picture")
    private String rentalPicture;

    @Column(name = "description")
    private String reantalDescription;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
