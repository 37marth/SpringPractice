package com.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//db가 id 자동생성
    private Long id;
    @Column
    private String name;
    @Column
    private String price;


    public void patch(Coffee coffee) {
        if(coffee.name!=null)
            this.name=coffee.name;
        if(coffee.price!=null)
            this.price=coffee.price;

    }
}