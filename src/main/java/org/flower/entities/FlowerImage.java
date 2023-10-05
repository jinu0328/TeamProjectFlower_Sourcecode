package org.flower.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "flowerimage")

public class FlowerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

    @ManyToOne
    @JoinColumn(name = "flowerNo")
    private Flower flower;

    @Column(length = 255)
    private String imagePath;

}


