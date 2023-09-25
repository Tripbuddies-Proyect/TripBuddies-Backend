package com.upc.TRIPBUDDIES.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "places")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Places implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tittle", nullable = false, length = 50)
    private String name;
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Column(name = "image_url", nullable = false, length = 2000)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "destino", nullable = false, length = 50)
    private String destino;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false, length = 50)
    private Date Date;

    @Column(name = "hora", nullable = false, length = 50)
    private String Hour;

    @Column(name = "origen", nullable = false, length = 50)
    private String origen;


    @ManyToOne
    @JoinColumn(name = "carriers_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private carrier carriers;
}
