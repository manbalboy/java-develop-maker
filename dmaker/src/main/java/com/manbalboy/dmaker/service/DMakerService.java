package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.entity.DeveloperLevel;
import com.manbalboy.dmaker.entity.DeveloperSkillType;
import com.manbalboy.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;


    @Transactional
    public void createDeveloper() {
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("Olaf")
                .memberId("test")
                .age(5)
                .build();

        developerRepository.save(developer);
    }
}
