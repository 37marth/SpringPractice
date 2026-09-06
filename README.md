# SpringPractice

《코딩 자율학습 스프링 부트 3 자바 백엔드 개발 입문》의 예제를
직접 작성하고 실행하며 학습한 저장소입니다.

교재 예제와 셀프체크를 바탕으로 Spring Boot의 웹 서비스 개발 흐름을 익힌
학습용 프로젝트입니다.

## 학습 기간과 진행 상태

- 학습 기간: 2026.04 ~ 2026.09
- 교재 1~19장 학습 완료
- 교재에서 안내한 동영상 강의를 참고하여 PostgreSQL 연결 실습

## 사용 기술

- Java 17, Spring Boot 3.1.0, Gradle
- Spring Web, Spring Data JPA, Lombok
- Mustache, Bootstrap, JavaScript
- H2, PostgreSQL
- JUnit 5, Spring Boot Test

## 학습 내용

- **게시글 CRUD**: Mustache 화면과 Spring MVC를 이용한 등록·조회·수정·삭제
- **데이터 저장**: 폼 데이터를 DTO로 받고 Entity로 변환하여 Repository로 저장
- **REST API**: GET·POST·PATCH·DELETE 요청, JSON 데이터, `ResponseEntity` 응답 처리
- **서비스 계층과 트랜잭션**: REST API의 Controller-Service-Repository 역할 분리와 `@Transactional` 실습
- **댓글 CRUD**: 게시글과 댓글의 관계, 댓글 API와 화면 구현
- **화면과 API 연결**: JavaScript 클릭 이벤트, `fetch()`, 수정 모달에 데이터 전달
- **SQL과 DB 연동**: SQL 조회·변경, H2 기반 실습 후 PostgreSQL 연결
- **테스트 코드**: JUnit을 이용한 게시글 Service와 댓글 Repository 테스트 작성

## 코드 찾아보기

| 학습 주제 | 관련 코드 |
| --- | --- |
| 게시글 화면과 폼 요청 | [ArticleController.java](src/main/java/com/example/firstproject/controller/ArticleController.java), [게시글 화면](src/main/resources/templates/articles) |
| 게시글 REST API와 서비스 | [ArticleApiController.java](src/main/java/com/example/firstproject/api/ArticleApiController.java), [ArticleService.java](src/main/java/com/example/firstproject/service/ArticleService.java) |
| 게시글·댓글의 관계 | [Comment.java](src/main/java/com/example/firstproject/entity/Comment.java), [CommentRepository.java](src/main/java/com/example/firstproject/repository/CommentRepository.java) |
| 댓글 API와 서비스 | [CommentApiController.java](src/main/java/com/example/firstproject/api/CommentApiController.java), [CommentService.java](src/main/java/com/example/firstproject/service/CommentService.java) |
| JavaScript 댓글 등록·수정·삭제 | [_new.mustache](src/main/resources/templates/comments/_new.mustache), [_list.mustache](src/main/resources/templates/comments/_list.mustache) |
| 테스트 코드 | [ArticleServiceTest.java](src/test/java/com/example/firstproject/service/ArticleServiceTest.java), [CommentRepositoryTest.java](src/test/java/com/example/firstproject/repository/CommentRepositoryTest.java) |


## 참고 자료

- 홍팍 지음, 길벗 《코딩 자율학습 스프링 부트 3 자바 백엔드 개발 입문》
- 교재에서 안내한 PostgreSQL 연동 동영상 강의
