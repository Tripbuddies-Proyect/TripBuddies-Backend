package com.upc.TRIPBUDDIES.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reviewText", nullable = false, length = 500)
    private String reviewText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveller_id")
    private Traveller traveller;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Places places;
}
