package com.upc.TRIPBUDDIES.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adquisicions")
public class Adquisicions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travellerId", nullable = false)
    private Traveller traveller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placesId", nullable = false)
    private Places places;

    @Column(name = "date", nullable = false)
    private String date;
    



}


