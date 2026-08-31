package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j//Simple Logging Facade for Java,로깅기능
public class ArticleController {
    @Autowired //스프링부트가 미리 생성해 놓은 리포지토리 객체 주입(DI)
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
//        데이터 생성
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new"; //파일경로/파일이름
    }

//        단일 데이터 조회
    @GetMapping("/articles/{id}")//컨트롤러에서 url 변수를 사용할때는 중괄호 하나만
    public String show(@PathVariable Long id, Model model) {
//        @PathVariableurl 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져옴
        log.info("id="+id);
//        1. id를 조회해 데이터 가져오기
        Article articleEntity=articleRepository.findById(id).orElse(null);
        //댓글 목록 가져오기
        List<CommentDto> commentDtos = commentService.comments(id);
//        findById(id)의 optional타입반환.
//        Article엔티티를 옵셔널로 감싸주거나 orElse(null)붙이기

//        2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);

//        3. 뷰 페이지 반환하기
        return "articles/show";
    }

//        전체 데이터 조회
    @GetMapping("/articles")
    public String index(Model model){
//        1.모든 데이터 가져오기
          List<Article> articleEntityList=articleRepository.findAll();
//        findAll()은 Iterable타입 반환,articleEntityList은 List타입이므로 타입이 서로 안맞음
//        해결법:findAll()을(List<Article>)로 다운캐스팅하기 or
//              articleEntityList타입을 List<Article>에서 Iterable<Article>로 업캐스팅하기 or
//              findAll()이 List 대신 ArrayList를 반환하게 하기
//        2.모델에 데이터 등록하기
          model.addAttribute("articleList",articleEntityList);
//        3.뷰 페이지 설정하기
        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")//mustache(뷰페이지)에서 변수는 {{id}}2개 컨트롤러 url은 {id}하나
    public String edit(@PathVariable Long id,Model model){
        Article articleEntity=articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/edit" ;
    }
    //데이터 수정
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
//        1.DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
//        2.엔티티를 DB에 저장하기
//        2-1.DB에 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
//        2-2 기존 데이터 값 갱신하기
        if(target!=null){
            articleRepository.save(articleEntity);  //id가 null → 새 데이터 등록 INSERT
                                                    //id가 있음 → 기존 데이터 수정 UPDATE
        }
//        3.수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/"+articleEntity.getId();
    }

    //데이터 생성.(행가로 ,열세로)
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        log.info(form.toString());
//        1.DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());//그냥 출력값 찍어보는 코드
//        2.레파짓토리로 엔티티를 db에 저장
        Article saved = articleRepository.save(article);
        log.info(article.toString());
//        System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    //데이터 삭제
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 정상적으로 들어왔습니다!!!");
//        1.삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
//        2.대상 엔티티 삭제하기
        if(target!=null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습돠!");
        }
//        3.결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }



}
