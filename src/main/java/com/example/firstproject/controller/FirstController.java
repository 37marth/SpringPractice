package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        //model 객체가 "홍팍" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "홍팍");//변숫값을 "변수명"이라는 이름으로 추가
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname","홍길동");
        return "goodbye";
    }
}
