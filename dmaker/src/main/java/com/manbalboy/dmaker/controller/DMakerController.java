package com.manbalboy.dmaker.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/developers")
public class DMakerController {

    @GetMapping
    public List<String> getAllDevelopers() {

        log.info("GET /developers Http/1.1");

        return Arrays.asList("snow", "Elsa", "Olaf");

    }
}
