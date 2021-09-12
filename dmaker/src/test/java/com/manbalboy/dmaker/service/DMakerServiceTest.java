package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.dto.DeveloperDto;
import com.manbalboy.dmaker.type.DeveloperLevel;
import com.manbalboy.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class DMakerServiceTest {

    @Autowired
    private DMakerService dMakerService;


    @Test
    public void testSomething() {
        dMakerService.createDeveloper(CreateDeveloper.Request
                .builder()
                .developerLevel(DeveloperLevel.SENIOR)
                .developerSkillType(DeveloperSkillType.FULl_STACK)
                .experienceYears(12)
                .memberId("manbalboy")
                .name("name")
                .age(32)
                .build()
        );
        List<DeveloperDto> allEmployedDevelopers = dMakerService.getAllEmployedDevelopers();

        System.out.println("============================");
        System.out.println(allEmployedDevelopers);
        System.out.println("============================");
    }

}