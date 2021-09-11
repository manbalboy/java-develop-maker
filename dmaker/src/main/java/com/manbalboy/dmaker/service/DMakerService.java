package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.entity.DeveloperLevel;
import com.manbalboy.dmaker.exception.DMakerException;
import com.manbalboy.dmaker.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.manbalboy.dmaker.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.manbalboy.dmaker.exception.DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

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
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);


        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .name(request.getName())
                .memberId(request.getMemberId())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYear = request.getExperienceYears();

        if (developerLevel == DeveloperLevel.SENIOR && experienceYear < 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR && (experienceYear < 4
                || experienceYear > 10)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR && experienceYear > 4) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }
}
