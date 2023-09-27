package com.upc.TRIPBUDDIES.entities;


import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date", nullable = false)
    private String date;
    @ManyToOne
    @JoinColumn(name = "carrier_id",nullable = false)
    private carrier carrier;

    @ManyToOne
    @JoinColumn(name = "places_id",nullable = false)
    private Places places;

    @Column(name="amount", nullable = false)
    private float amount;


}