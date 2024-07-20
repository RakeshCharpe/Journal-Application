package com.hustlertechnology.JournalApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }
    @PostMapping("/health-check")
    public String addHealth(){
        return "added successfully";
    }
}
