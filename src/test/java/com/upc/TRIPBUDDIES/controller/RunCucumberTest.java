package com.upc.TRIPBUDDIES.controller;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // o la ruta donde tengas tus archivos .feature
        glue = "com.upc.TRIPBUDDIES.controller"
)
public class RunCucumberTest {
    // no se necesita implementar nada aquí
}
