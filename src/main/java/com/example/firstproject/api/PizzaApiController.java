package com.example.firstproject.api;

import com.example.firstproject.entity.Pizza;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PizzaApiController {

    //전체조회
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<Pizza>> index(){

        return null;
    }
    //단건조회
    //생성
    //수정
    //삭제
}
