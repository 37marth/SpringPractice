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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup") //로그인
    public String signUpPage(){

        return "members/new";
    }

    @PostMapping("/join") //회원가입
    public String join(MemberForm memberForm){
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();

        log.info(memberForm.toString());
        Member saved = memberRepository.save(member);

        log.info(memberForm.toString());
        return "redirect:members/"+saved.getId();
    }

    @GetMapping("/members/{id}") //단일데이터조회
    public String show(@PathVariable Long id, Model model){
//        1. id를 조회해 데이터 엔티티를 가져오기
        Member memberEntity =memberRepository.findById(id).orElse(null);
//        2. 모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        return "members/show";
    }

    @GetMapping("/members") //전체 데이터 조회
    public String index(Model model){
//        1.DB에서 모든 Member 데이터 가져오기 (List형태)
        List<Member> memberEntityList=memberRepository.findAll();
//        2.가져온 Member 묶음을 모델에 등록하기
        model.addAttribute("memberList",memberEntityList);
//        3.뷰 페이지 설정하기
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")//단일 데이터 수정
    public String edit(@PathVariable Long id,Model model){
        Member memberEntity=memberRepository.findById(id).orElse(null); //id로 찾아서 있는 데이터를
        model.addAttribute("member",memberEntity); //모델이 사용할수 있게 변수로 넘겨주기
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
//        1.DTO를 엔티티로 변환하기
        Member memberEntity = form.toEntity();
//        2.엔티티를 DB에 저장하기
//        2-1.DB에 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
//        2-2 기존 데이터 값 갱신하기
        if(target!=null){
            memberRepository.save(memberEntity);
        }
//        3.수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/"+memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
//        1.삭제할 대상 가져오기
        Member target=memberRepository.findById(id).orElse(null);
//        2.대상 엔티티 삭제하기
        if(target!=null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제완료되었스무니다!");
        }
//        3.결과 페이지로 리다이렉트하기
        return "redirect:/members";
    }





}
