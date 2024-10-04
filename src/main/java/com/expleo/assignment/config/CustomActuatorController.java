package com.expleo.assignment.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomActuatorController {

    @GetMapping("/custom-actuator")
    public String customActuatorEndpoint() {
        return "This is a custom actuator endpoint!";
    }
}

