package com.example.library.repository;

import com.example.library.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByIdCardNumber(String idCardNumber);
}
