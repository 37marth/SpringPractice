package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/selfcheck/selfchecknew")
    public String selfcheckNew(){
        return "selfcheck/selfchecknew";
    }

    @PostMapping("/join")
    public String selfcheckCreate(MemberForm memberForm){
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        return "";


    }

}
