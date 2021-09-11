package com.manbalboy.dmaker.controller;


import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.dto.DeveloperDetailDto;
import com.manbalboy.dmaker.dto.DeveloperDto;
import com.manbalboy.dmaker.dto.EditDeveloper;
import com.manbalboy.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {

    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {

        log.info("GET /developers Http/1.1");

        return dMakerService.getAllDevelopers();

    }

    @GetMapping("/developer/{memeberId}")
    public DeveloperDetailDto getAllDevelopers(@PathVariable String memeberId) {

        log.info("GET /developers Http/1.1");

        return dMakerService.getDeveloperDetail(memeberId);

    }

    @PostMapping("/developer")
    public CreateDeveloper.Response createDevelopers(@RequestBody CreateDeveloper.Request request) {

        log.info("request {}", request);

        return dMakerService.createDeveloper(request);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @RequestBody EditDeveloper.Request request) {

        log.info("request {}", request);

        return dMakerService.editDeveloper(memberId, request);

    }
}
