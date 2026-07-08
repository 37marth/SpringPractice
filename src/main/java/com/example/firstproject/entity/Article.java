package com.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//db가 id 자동생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;



}