package com.manbalboy.dmaker.controller;


import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;


    @GetMapping("/developers")
    public List<String> getAllDevelopers() {

        log.info("GET /developers Http/1.1");

        return Arrays.asList("snow", "Elsa", "Olaf");

    }


    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(@RequestBody CreateDeveloper.Request request) {

        log.info("request {}", request);

        return dMakerService.createDeveloper(request);
    }
}
