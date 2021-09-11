package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;

    // ACID
    // Atomic
    // Consistency
    // Isolation
    // Durability
    @Transactional
    public void createDeveloper(CreateDeveloper.Request request) {
        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYear())
                .name(request.getName())
                .memberId(request.getMemberId())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
    }
}
