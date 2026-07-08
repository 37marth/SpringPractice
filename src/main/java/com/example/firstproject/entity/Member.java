package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//db가 id 자동생성
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

}
