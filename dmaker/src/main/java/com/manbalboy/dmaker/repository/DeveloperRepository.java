package com.manbalboy.dmaker.repository;

import com.manbalboy.dmaker.entity.Developer;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByMemberId(String memberId);


 }
