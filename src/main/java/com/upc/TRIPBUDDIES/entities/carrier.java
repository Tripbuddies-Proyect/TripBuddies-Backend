package com.upc.TRIPBUDDIES.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carriers")
@Data
@NoArgsConstructor
public class carrier extends User {
    private String ruc;
    private String address;
    private String country;
    private String city;
    private String Plate;
    private String Marca;

    public carrier(Long id, String firstName, String lastName, String email, String phone, String password, String role, String description, String image, String ruc, String address, String country, String city, String bannerImage, String Plate, String Marca) {
        super(id, firstName, lastName, email, phone, password, role, description, image, bannerImage);
        this.ruc = ruc;
        this.address = address;
        this.country = country;
        this.city = city;
        this.Plate = Plate;
        this.Marca = Marca;
    }
}
