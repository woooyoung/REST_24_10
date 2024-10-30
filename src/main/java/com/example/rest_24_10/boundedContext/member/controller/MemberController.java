package com.example.rest_24_10.boundedContext.member.controller;

import com.example.rest_24_10.boundedContext.member.entity.Member;
import com.example.rest_24_10.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public Member login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse resp) {

        resp.addHeader("Authentication", "JWT 토큰");

        return memberService.findByUsername(loginRequest.getUsername()).orElse(null);
    }

}
