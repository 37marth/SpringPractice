package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ArticleController {
    @Autowired //스프링부트가 미리 생성해 놓은 리포지토리 객체 주입(DI)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new"; //파일경로/파일이름
    }

    @PostMapping("/articles/create") //행가로 ,열세로
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
//        1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());//그냥 출력값 찍어보는 코드
//        2.레파짓토리로 엔티티를 db에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }


}
