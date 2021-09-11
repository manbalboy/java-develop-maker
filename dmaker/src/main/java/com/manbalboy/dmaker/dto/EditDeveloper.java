package com.manbalboy.dmaker.dto;

import com.manbalboy.dmaker.entity.DeveloperLevel;
import com.manbalboy.dmaker.entity.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class EditDeveloper {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NonNull
        private DeveloperSkillType developerSkillType;

        @NonNull
        private DeveloperLevel developerLevel;

        @NonNull
        @Min(0)
        @Max(20)
        private Integer experienceYears;
    }
}
