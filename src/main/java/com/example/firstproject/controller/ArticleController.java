package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j//Simple Logging Facade for Java,로깅기능
public class ArticleController {
    @Autowired //스프링부트가 미리 생성해 놓은 리포지토리 객체 주입(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new"; //파일경로/파일이름
    }

    @GetMapping("/articles/{id}")//컨트롤러에서 url 변수를 사용할때는 중괄호 하나만
    public String show(@PathVariable Long id) {
        //@PathVariableurl 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져옴
        log.info("id="+id);
        return "";
    }

    @PostMapping("/articles/create") //행가로 ,열세로
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        log.info(form.toString());
//        1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(form.toString());
//        System.out.println(article.toString());//그냥 출력값 찍어보는 코드
//        2.레파짓토리로 엔티티를 db에 저장
        Article saved = articleRepository.save(article);
        log.info(form.toString());
//        System.out.println(saved.toString());
        return "";
    }



}
