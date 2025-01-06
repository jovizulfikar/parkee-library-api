package com.example.library.controller;

import com.example.library.model.dto.CreateMemberRequest;
import com.example.library.model.dto.CreateMemberResponse;
import com.example.library.model.dto.GetMembersResponse;
import com.example.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<GetMembersResponse> getMembers() {
        var response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateMemberResponse> postMembers(
            @RequestBody @Validated CreateMemberRequest request
    ) {
        var response = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
