package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;


@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private String password;


    public Member toEntity() {
        return new Member(null, email, password);
    }
}
