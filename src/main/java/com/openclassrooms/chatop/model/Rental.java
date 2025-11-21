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

    @Column(name = "surface")
    private Double rentalSurface;

    @Column(name = "price")
    private Double rentalPrice;

    @Column(name = "picture")
    private String rentalPicture;

    @Column(name = "description")
    private String rentalDescription;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
