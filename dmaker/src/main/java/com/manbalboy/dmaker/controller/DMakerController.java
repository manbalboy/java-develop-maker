package com.manbalboy.dmaker.controller;


import com.manbalboy.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/developers")
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;


    @GetMapping
    public List<String> getAllDevelopers() {

        log.info("GET /developers Http/1.1");

        return Arrays.asList("snow", "Elsa", "Olaf");

    }


    @GetMapping("/create-developers")
    public List<String> createDevelopers() {

        log.info("GET /create-developers Http/1.1");

        dMakerService.createDeveloper();

        return Arrays.asList("snow", "Elsa", "Olaf");

    }
}
