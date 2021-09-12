package com.manbalboy.dmaker.service;

import com.manbalboy.dmaker.dto.CreateDeveloper;
import com.manbalboy.dmaker.dto.DeveloperDetailDto;
import com.manbalboy.dmaker.entity.Developer;
import com.manbalboy.dmaker.repository.DeveloperRepository;
import com.manbalboy.dmaker.repository.RetiredDeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.manbalboy.dmaker.code.StatusCode.EMPLOYED;
import static com.manbalboy.dmaker.type.DeveloperLevel.SENIOR;
import static com.manbalboy.dmaker.type.DeveloperSkillType.FULl_STACK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {
    @Mock
    RetiredDeveloperRepository retiredDeveloperRepository;

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DMakerService dMakerService;

    @Test
    public void testSomething() {
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerLevel(SENIOR)
                        .developerSkillType(FULl_STACK)
                        .age(38)
                        .experienceYears(12)
                        .name("HUN")
                        .memberId("manbalboy")
                        .statusCode(EMPLOYED)
                        .build()
                ));

        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("manbalboy");

        assertAll(
                () -> assertEquals(SENIOR, developerDetail.getDeveloperLevel()),
                () -> assertEquals(FULl_STACK, developerDetail.getDeveloperSkillType()),
                () -> assertEquals(38, developerDetail.getAge())
        );
    }

    @Test
    void test() {
        //given
        //Mocking, 지역변수
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(SENIOR)
                .developerSkillType(FULl_STACK)
                .age(38)
                .experienceYears(12)
                .name("HUN")
                .memberId("manbalboy")
                .build();

        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        given(developerRepository.save(any()))
                .willReturn(Developer.builder()
                        .developerLevel(SENIOR)
                        .developerSkillType(FULl_STACK)
                        .age(38)
                        .experienceYears(12)
                        .name("HUN")
                        .memberId("manbalboy")
                        .statusCode(EMPLOYED)
                        .build());

        ArgumentCaptor<Developer> captor =
                ArgumentCaptor.forClass(Developer.class);


        //when
        //동작, 결과갑을 받아오는것
        CreateDeveloper.Response developer = dMakerService.createDeveloper(request);

        //then
        //결과값 검증

        verify(developerRepository, times(1))
                .save(captor.capture());

        Developer saveDeveloper = captor.getValue();

        assertEquals(SENIOR, saveDeveloper.getDeveloperLevel());
        assertEquals(FULl_STACK, saveDeveloper.getDeveloperSkillType());
    }

}