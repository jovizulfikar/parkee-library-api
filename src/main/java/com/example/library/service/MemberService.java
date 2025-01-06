package com.example.library.service;

import com.example.library.exception.AppException;
import com.example.library.exception.Error;
import com.example.library.model.dto.CreateMemberRequest;
import com.example.library.model.dto.CreateMemberResponse;
import com.example.library.model.dto.GetMembersResponse;
import com.example.library.model.entity.Member;
import com.example.library.repository.MemberRepository;
import com.github.ksuid.Ksuid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public GetMembersResponse getMembers() {
        var members = memberRepository.findAll().stream()
                .map(GetMembersResponse.MemberDto::from)
                .toList();

        return GetMembersResponse.builder()
                .members(members)
                .build();
    }

    public CreateMemberResponse createMember(CreateMemberRequest request) {
        var member = memberRepository.findByIdCardNumber(request.getIdCardNumber())
                .orElse(null);

        if (Objects.nonNull(member)) {
            throw new AppException(Error.MEMBER_ID_CARD_REGISTERED);
        }

        member = Member.builder()
                .id(Ksuid.newKsuid().toString())
                .idCardNumber(request.getIdCardNumber())
                .name(request.getName())
                .build();

        memberRepository.save(member);

        return CreateMemberResponse.builder()
                .id(member.getId())
                .build();
    }
}
