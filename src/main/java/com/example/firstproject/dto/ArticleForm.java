package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //모든필드에 매개변수 있는 생성자 자동생성
@ToString //toString함수와 동일효과
public class ArticleForm {
    private String title;
    private String content;




    public Article toEntity() {
        return new Article(null, title, content);

    }
}
