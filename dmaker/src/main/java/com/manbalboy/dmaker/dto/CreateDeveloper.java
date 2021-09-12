package com.manbalboy.dmaker.dto;

import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.type.DeveloperLevel;
import com.manbalboy.dmaker.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {
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

        @NonNull
        @Size(min = 3, max = 50, message = "memberId size must 3~50")
        private String memberId;

        @NonNull
        @Size(min = 3, max = 20, message = "name size must 3~50")
        private String name;

        @Min(18)
        private Integer age;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private DeveloperSkillType developerSkillType;
        private DeveloperLevel developerLevel;
        private Integer experienceYears;
        private String memberId;

        public static Response fromEntity(@NotNull Developer developer) {
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }

}
