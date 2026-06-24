package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage(){

        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();

        log.info(memberForm.toString());
        Member saved = memberRepository.save(member);

        log.info(memberForm.toString());
        return "redirect:members/"+saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
//        1. id를 조회해 데이터 엔티티를 가져오기
        Member memberEntity =memberRepository.findById(id).orElse(null);
//        2. 모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
//        1.DB에서 모든 Member 데이터 가져오기 (List형태)
        List<Member> memberEntityList=memberRepository.findAll();
//        2.가져온 Member 묶음을 모델에 등록하기
        model.addAttribute("memberList",memberEntityList);
//        3.뷰 페이지 설정하기
        return "members/index";
    }



}
