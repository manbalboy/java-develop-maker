package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.constant.DMakerConstant;
import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.dto.DeveloperDetailDto;
import com.manbalboy.dmaker.dto.DeveloperDto;
import com.manbalboy.dmaker.dto.EditDeveloper;
import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.entity.RetiredDeveloper;
import com.manbalboy.dmaker.exception.DMakerException;
import com.manbalboy.dmaker.repository.DeveloperRepository;
import com.manbalboy.dmaker.repository.RetiredDeveloperRepository;
import com.manbalboy.dmaker.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.manbalboy.dmaker.code.StatusCode.EMPLOYED;
import static com.manbalboy.dmaker.code.StatusCode.RETIRED;
import static com.manbalboy.dmaker.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.manbalboy.dmaker.constant.DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS;
import static com.manbalboy.dmaker.type.DMakerErrorCode.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // 1.employed -> retired
        Developer developer = developerRepository
                .findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));

        developer.setStatusCode(RETIRED);

        // 2. save into retiredDeveloper

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .id(developer.getId())
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);

        return DeveloperDetailDto.fromEntity(developer);

    }

    // ACID
    // Atomic
    // Consistency
    // Isolation
    // Durability
    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        return CreateDeveloper.Response.fromEntity(
                developerRepository.save(createDeveloperFromRequest(request))
        );
    }

    private Developer createDeveloperFromRequest(CreateDeveloper.Request request) {
        return Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .name(request.getName())
                .memberId(request.getMemberId())
                .statusCode(EMPLOYED)
                .age(request.getAge())
                .build();
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllEmployedDevelopers() {

        return developerRepository.findDevelopersByStatusCodeEquals(EMPLOYED)
                .stream()
                .map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(
            String memberId,
            EditDeveloper.Request request) {

        validateEditDeveloperRequest(request);

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(
                        () -> new DMakerException(NO_DEVELOPER)
                );

        log.info("develop info : {}", developer);
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(
            @NotNull EditDeveloper.Request request
    ) {
        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYears()
        );
    }

    private void validateCreateDeveloperRequest(
            @NotNull CreateDeveloper.Request request) {
        validateDeveloperLevel(
                request.getDeveloperLevel()
                , request.getExperienceYears()
        );

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }

    private void validateDeveloperLevel(
            @NotNull DeveloperLevel developerLevel,
            @NotNull Integer experienceYear) {
        if (developerLevel == DeveloperLevel.SENIOR && experienceYear < MIN_SENIOR_EXPERIENCE_YEARS) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR &&
                (experienceYear < MAX_JUNIOR_EXPERIENCE_YEARS || experienceYear > MIN_SENIOR_EXPERIENCE_YEARS)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.JUNGNIOR && experienceYear > MAX_JUNIOR_EXPERIENCE_YEARS) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }


}
