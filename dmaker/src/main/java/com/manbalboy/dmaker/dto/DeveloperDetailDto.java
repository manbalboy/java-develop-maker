package com.manbalboy.dmaker.dto;

import com.manbalboy.dmaker.code.StatusCode;
import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.type.DeveloperLevel;
import com.manbalboy.dmaker.type.DeveloperSkillType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {
    protected Long id;
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYears;
    private StatusCode statusCode;
    private String memberId;
    private String name;
    private Integer age;

    public static DeveloperDetailDto fromEntity(Developer developer) {
        return DeveloperDetailDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .statusCode(developer.getStatusCode())
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .age(developer.getAge())
                .id(developer.getId())
                .build();
    }
}
