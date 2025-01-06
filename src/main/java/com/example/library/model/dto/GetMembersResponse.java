package com.example.library.model.dto;

import com.example.library.model.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class GetMembersResponse {

    @Builder.Default
    private List<MemberDto> members = new ArrayList<>();

    @Builder
    @Getter
    public static class MemberDto {
        private String id;
        private String idCardNumber;
        private String name;

        public static MemberDto from(Member member) {
            return MemberDto.builder()
                    .id(member.getId())
                    .idCardNumber(member.getIdCardNumber())
                    .name(member.getName())
                    .build();
        }
    }
}
