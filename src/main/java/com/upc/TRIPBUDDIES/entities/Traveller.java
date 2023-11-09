package com.upc.TRIPBUDDIES.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "travellers")
@Data
@NoArgsConstructor
public class Traveller extends User {
    public Traveller(Long id, String firstName, String lastName, String email, String phone, String password, String role, String description, String image, String bannerImage) {
        super(id, firstName, lastName, email, phone, password, role, description, image, bannerImage);
    }
}
