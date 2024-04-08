# TechTalk
개발자들의 지식 공유 커뮤니티 TechTalk 입니다.



<br></br>
## 프로젝트 설명 및 기능
- 프로젝트 설명
    - 개발자 및 프로그래밍에 관심있는 사람들간 서로의 경험과 지식을 공유하며 자유롭게 Q&A 할 수 있는 플랫폼
    - 각 카테고리 별 커뮤니티 서비스 제공 플랫폼

- 기능
    - 회원
        - 회원가입/로그인/로그아웃 기능
        - 닉네임 수정 기능
        - 게시글 작성/조회/수정/검색 삭제 기능
        - 댓글 작성/수정/삭제 기능
    
    - 관리자
        - 로그인/로그아웃 기능
        - 공지사항 작성/수정/삭제 기능
        - 회원 조회/검색/삭제 기능

## 팀원 소개 😊
이영석(PL)  
류석현  
최보현


## 프로젝트 사용 기술 및 환경

FE

- HTML
- CSS
- BootStrap

BE 

- SpringBoot
- Java
- Thymeleaf

DB

- MySQL

환경

- AWS
- Intellij
- Github
- Discord + 웹훅을 통한 깃헙 알림 설정

## 개발 일정

## Flow Chart  

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/1a9b8232-47ee-4263-9be0-8fea14804e68)



## 요구사항 및 기능 명세  
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/8759b35e-0b31-47c7-8407-6ad7dc1e8f91)


## 데이터베이스 모델링(ERD)
<br></br>
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/4695ba89-5de1-417d-9f88-d4aa3ef8e258)


## 배포 아키텍처  
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/0c6b9539-d7ac-4223-a1d2-a4c6e2a521ab)

## API 명세서

➰ :  표시 중 최소 1개 필요

✔️ :  체크 표시만 가능

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/cf2025c4-e728-4be9-84dc-c5569c145213)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/8fe72f45-8c90-4d27-b30b-ed7b165985c4)


## 프로젝트 구조
- FE + BE

📂 gradle  
📂 src  
┣ 📂 main  
┃ ┗ 📂 java  
┃ ┃ ┗ 📂 community  
┃ ┃ ┃ ┗ 📂 coomon  
┃ ┃ ┃ ┃ ┗ 📜 TimeStamp.class  
┃ ┃ ┃ ┃ ┗ 📂 config  
┃ ┃ ┃ ┃ ┃ ┗ 📜 InterceptorConfig.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 SecurityConfiguration   
┃ ┃ ┃ ┃ ┃ ┗ 📜 SwaggerConfig  
┃ ┃ ┃ ┃ ┗ 📂 constant  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryType.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 Role  
┃ ┃ ┃ ┃ ┗ 📂 controller  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleController.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticlePageController.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentController.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserController.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserViewController.class  
┃ ┃ ┃ ┃ ┗ 📂 domain  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleCategoryEntity.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleEntity.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentEntity.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryEntity.class   
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserEntity.class   
┃ ┃ ┃ ┃ ┗ 📂 dto  
┃ ┃ ┃ ┃ ┃ ┗ 📜 AddUserRequest.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleCategoryDto.class   
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleDto.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentDto.class    
┃ ┃ ┃ ┃ ┃ ┗ 📜 CheckDuplicateRequest.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryDto.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 DeleteUserIdsRequest.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserDto.class  
┃ ┃ ┃ ┃ ┗ 📂 exception  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleNotFoundException  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UnauthorizedException  
┃ ┃ ┃ ┃ ┗ 📂 intreceptor  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleAuthInterceptor 
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentAuthInterceptor  
┃ ┃ ┃ ┃ ┗ 📂 mapper  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleCategoryMapper  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleMapper  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentMapper  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryMapper  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserMapper  
┃ ┃ ┃ ┃ ┗ 📂 repository  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleCategoryRepository.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleRepository.class   
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentRepository.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryRepository.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserRepository.class  
┃ ┃ ┃ ┃ ┗ 📂 service  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleCategoryService.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 ArticleService.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CommentService.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 CategoryService.class  
┃ ┃ ┃ ┃ ┃ ┗ 📜 UserService.class  
┃ ┃ ┃ ┗ 📜 Init.class  
┃ ┗ 📂 resources  
┃ ┃ ┗ 📂 static   
┃ ┃ ┃ ┗ 📂 css  
┃ ┃ ┃ ┃ ┗ 📜 AdminPage.css  
┃ ┃ ┃ ┃ ┗ 📜 ArticleCreate.css  
┃ ┃ ┃ ┃ ┗ 📜 articleList.css  
┃ ┃ ┃ ┃ ┗ 📜 custom.css  
┃ ┃ ┃ ┃ ┗ 📜 join.css  
┃ ┃ ┃ ┃ ┗ 📜 login.css  
┃ ┃ ┃ ┃ ┗ 📜 style.css  
┃ ┃ ┃ ┃ ┗ 📜 userPage.css  
┃ ┃ ┗ 📂 js  
┃ ┃ ┃ ┗ 📜 admin.js  
┃ ┃ ┃ ┗ 📜 article.js  
┃ ┃ ┃ ┗ 📜 articleList.js  
┃ ┃ ┃ ┗ 📜 join.js  
┃ ┃ ┃ ┗ 📜 main.js  
┃ ┃ ┃ ┗ 📜 userInfo.js  
┃ ┗ 📂 templates   
┃ ┃ ┗ 📜 bootstrapForm.html  
┃ ┃ ┗ 📜 adminPage.html  
┃ ┃ ┗ 📜 articleDetail.html  
┃ ┃ ┗ 📜 articleList.html  
┃ ┃ ┗ 📜 Join.html  
┃ ┃ ┗ 📜 login.html  
┃ ┃ ┗ 📜 main.html  
┃ ┃ ┗ 📜 userPage.html  
┃ ┃ ┗ 📜 writeArticle.html  
┗ 📂 test  
┣ 📜 gradlew    
┣ 📜 gradlew.bat    
┣ 📜 README.md    
┣ 📜 settings.gradle    

## UI

1. main.html / 메인페이지 - 상단바(로그인 안하는 경우)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/013ffc5a-e946-4c98-ae52-9520be40d597)


1. main.html / 메인페이지 - 상단바(로그인 한 경우)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/064def2f-a165-4bf0-bf3e-b1714e2c7d94)


1. articleList.html / 게시물 목록 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/c2798976-6514-4a3a-9e88-9a88475b7250)

1. articleDetail / 게시물 상세 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/5265c064-cb02-4b95-a2cc-ef50a428915b)

1. writeArticle.html / 글 등록 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/e905ad55-20a5-4924-b0c5-eac718c2247c)


5-1. writeArticle.html / 글 수정 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/585834c8-a8bc-4624-b404-ad8dc54a6d91)
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/2189c65f-43ee-4340-82f9-dc887e97aaae)


1. adminPage.html / 관리자 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/42c324d4-1b50-48d0-a52c-144d0b0d33b9)


1. userPage.html / 회원 정보 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/27e1eb53-7fb4-4557-a6f7-ef9e61aa18c7)

1. login.html / 로그인 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/c5923982-e5c7-4bd7-8381-82319491d147)

1. join.html / 회원가입 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/683ae25a-facc-48bf-aa8a-880fc1edc4c1)

## 개발 이슈

[ERD 수정](https://github.com/teamSeven71/TechTalk/issues/79)  
[댓글 영속성 문제 발생](https://github.com/teamSeven71/TechTalk/issues/81)
