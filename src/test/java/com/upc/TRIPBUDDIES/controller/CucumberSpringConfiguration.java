package com.upc.TRIPBUDDIES.controller;

import com.upc.TRIPBUDDIES.TripbuddiesApplication;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = TripbuddiesApplication.class) // Aquí debes referenciar tu clase principal de Spring Boot
public class CucumberSpringConfiguration {
    // No es necesario agregar nada dentro del cuerpo de la clase
}
