package com.upc.TRIPBUDDIES.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


    @Data
    @Entity
    @Table(name = "saleTable")
    @AllArgsConstructor
    @NoArgsConstructor
    public class SaleTable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name="places_id")
        private Places places;
        @ManyToOne
        @JoinColumn(name="user_id")
        private User user;


    }

