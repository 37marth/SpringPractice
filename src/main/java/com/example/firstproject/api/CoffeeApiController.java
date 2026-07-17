package com.example.firstproject.api;


import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController

public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){return coffeeRepository.findById(id).orElse(null);}

    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto){
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,@RequestBody CoffeeDto dto){
        // 1. 요청 본문의 DTO를 Coffee 엔티티로 변환하기
        // 힌트: 바로 위 create() 메서드에서는 DTO를 어떻게 변환했는지 살펴보기
        Coffee coffee = dto.toEntity();
        // 2. URL로 받은 id를 이용해 기존 Coffee 데이터 찾기
        // 힌트: show() 메서드에서는 id로 데이터를 어떻게 조회했는지 떠올리기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. 수정할 대상과 요청이 올바른지 확인하기
        // 힌트: 기존 데이터가 없는 경우와 URL의 id가 요청 데이터의 id와 다른 경우 생각하기
        if (target==null||!(id.equals(coffee.getId()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 기존 Coffee 객체에 새로 전달받은 값 반영하기
        // 힌트: Coffee 엔티티에 부분 수정을 도와주는 메서드가 있는지 찾아보기
        target.patch(coffee);

        // 5. 변경된 Coffee 객체를 리파지터리를 통해 저장하기
        // 힌트: create()에서 엔티티를 저장할 때 사용한 메서드 떠올리기
        Coffee updated = coffeeRepository.save(target);

        // 6. 수정된 Coffee와 알맞은 HTTP 상태를 응답으로 반환하기
        // 힌트: 이 메서드의 반환형이 Coffee를 감싸고 있는 이유 떠올리기
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        // 1. DELETE 요청에 정말 필요한 입력값이 무엇인지 확인하기
        // 힌트: 삭제할 대상을 정하려면 URL의 id만으로 충분한지, 요청 본문 DTO도 필요한지 생각하기

        // 2. URL로 받은 id를 이용해 삭제할 Coffee 데이터 찾기
        // 힌트: update()에서 기존 데이터를 찾았던 방법을 떠올리기
        Coffee target = coffeeRepository.findById(id).orElse(null);

        // 3. 삭제할 대상이 존재하지 않는 경우 처리하기
        // 힌트: update()에서는 target이 없을 때 어떤 상태의 응답을 반환했는지 살펴보기

        if (target==null||!(id.equals(target.getId()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 찾은 Coffee 객체를 리파지터리를 통해 삭제하기
        // 힌트: CrudRepository가 제공하는 메서드 중 저장의 반대 역할을 하는 메서드 찾아보기

         coffeeRepository.delete(target);
        // 5. 삭제 성공을 알리는 HTTP 응답 반환하기
        // 힌트: 삭제 후 Coffee 데이터를 본문에 보낼지, 상태만 보낼지 먼저 결정하기
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
