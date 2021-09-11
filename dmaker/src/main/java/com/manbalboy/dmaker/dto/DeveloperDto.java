package com.manbalboy.dmaker.dto;

import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.entity.DeveloperLevel;
import com.manbalboy.dmaker.entity.DeveloperSkillType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDto {
    private DeveloperSkillType developerSkillType;
    private DeveloperLevel developerLevel;
    private Integer experienceYears;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .memberId(developer.getMemberId())
                .build();
    }
}
