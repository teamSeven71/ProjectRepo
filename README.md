# 🌱 TechTalk
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

<br></br>
## 팀원 소개 😊
이영석(PL)  
류석현  
최보현

<br></br>
## 💻 프로젝트 사용 기술 및 환경

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

<br></br>
## 🗓️ 개발 일정  
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/79041981-d030-4cc8-b833-087445a07fee)

<br></br>
## 🖌️ Flow Chart  

처음 메인 페이지에 들어가면 관리자가 올린 공지사항, 글을 올릴 수 있는 여러 게시판 카테고리 선택란, 네비게이션 상단바가 보이게 됩니다.  
크게 세 개로 나누어 흐름도를 작성하였습니다.

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/1a9b8232-47ee-4263-9be0-8fea14804e68)


<br></br>
## 요구사항 및 기능 명세  
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/8759b35e-0b31-47c7-8407-6ad7dc1e8f91)

<br></br>
## 데이터베이스 모델링(ERD)  

① 유저와 관리자의 속성 값이 일치하여 하나의 테이블(유저 테이블)에서 Enum으로만 USER / ADMIN 역할 분리  
② 정규화를 위해 기존 게시글(사용자 작성 게시글, 관리자 작성 게시글) 관련 테이블 -> Article, ArticleCategory, Category 테이블로 변경  
③ 사용자와 댓글 데이터간의 접근 차원을 줄이기 위해 유저 테이블과 댓글 테이블간 연관 관계 수정  


<br></br>
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/4695ba89-5de1-417d-9f88-d4aa3ef8e258)

<br></br>
## 배포 아키텍처  

사용자가 URL을 통해 ec2 인스턴스에 빌드된 스프링부트 서버에 접속요청을 보내면, 
서버는 RDS에 있는 MySQL DB에 메인 페이지의 thymeleaf에서 필요한 공지사항, 카테고리 종류 데이터를 요청하고, HTML,CSS(Bootstrap),JS를 구성해 사용자에게 요청한 View를 반환해줍니다.  
IntelliJ에서 작업한 작업내용은 Github Repo에 저장하며, commit, merge, pr 등 레포지토리에서 발생하는 모든 변화는 디스코드 웹훅을 통해 즉시 알림메세지 형태로 전달됩니다.
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/0c6b9539-d7ac-4223-a1d2-a4c6e2a521ab)

<br></br>
## API 명세서

➰ :  표시 중 최소 1개 필요

✔️ :  체크 표시만 가능

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/cf2025c4-e728-4be9-84dc-c5569c145213)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/8fe72f45-8c90-4d27-b30b-ed7b165985c4)

<br></br>
## 🤓 프로젝트 구조
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

<br></br>
##🎨 UI

 main.html / 메인페이지 - 상단바(로그인 안하는 경우)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/013ffc5a-e946-4c98-ae52-9520be40d597)


 main.html / 메인페이지 - 상단바(로그인 한 경우)

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/064def2f-a165-4bf0-bf3e-b1714e2c7d94)


 articleList.html / 게시물 목록 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/c2798976-6514-4a3a-9e88-9a88475b7250)

 articleDetail / 게시물 상세 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/5265c064-cb02-4b95-a2cc-ef50a428915b)

 writeArticle.html / 글 등록 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/e905ad55-20a5-4924-b0c5-eac718c2247c)


 writeArticle.html / 글 수정 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/585834c8-a8bc-4624-b404-ad8dc54a6d91)
![image](https://github.com/teamSeven71/TechTalk/assets/109260733/2189c65f-43ee-4340-82f9-dc887e97aaae)


 adminPage.html / 관리자 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/42c324d4-1b50-48d0-a52c-144d0b0d33b9)


 userPage.html / 회원 정보 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/27e1eb53-7fb4-4557-a6f7-ef9e61aa18c7)

 login.html / 로그인 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/c5923982-e5c7-4bd7-8381-82319491d147)

 join.html / 회원가입 페이지

![image](https://github.com/teamSeven71/TechTalk/assets/109260733/683ae25a-facc-48bf-aa8a-880fc1edc4c1)

## 개발 이슈

[ERD 수정](https://github.com/teamSeven71/TechTalk/issues/79)  
[댓글 영속성 문제 발생](https://github.com/teamSeven71/TechTalk/issues/81)  
[배포 단계에서 에러](https://github.com/teamSeven71/TechTalk/issues/85)  
[댓글 영속성 문제 발생](https://github.com/teamSeven71/TechTalk/issues/81)

<br></br>
## 개발 회고
🧑‍💻 류석현  
    
    짧은 개발 기간 안에 설계부터 배포까지 모두 해야만하는 프로젝트여서 코드 작성할 때 많은 오류를 겪었습니다. 그 중에서 가장 큰 배움은 TDD의 중요성입니다.  이번 개발과정에서는 API 하나부터 클라이언틀와 서버의 정보교환까지 모든 코드 부분을 실행하려면 직접 로컬서버를 실행하거나 POSTMAN을 실행시켜봐야 알 수 있었습니다. 이 과정에서 시간이 많이 소요됐고 다양한 경우의 수를 사전에 test 해보지 못해서 다른 코드와도 얽혀서 작동에 문제가 생기는 경우도 많았습니다.  
    
    따라서 ,TDD를 구축하고 활용한다면 기존의 테스트 케이스가 새로운 코드의 동작을 검증해주기 때문에 예상치 못한 버그를 방지할 수 있을 것이고  코드의 재사용성과 유지보수성에도 큰 역할을 할수있다고 생각합니다.
    
    너무 너무 짧고 길게 시간이 흐른것 같습니다. 직접 프로젝트를 해보고 부딪혀 보면서 정말 정말 많은 경험과 배움을 얻은 것 같습니다. 부득이하게 적은 인원으로 프로젝트를 진행하게됐지만 다른 팀원분들이 너무 잘 도와주시고 역할을 수행해주셔서 프로젝트를 잘 마무리했습니다 
    
🧑‍💻 이영석  
    
    설계가 중요하다는 이야기는 많이 들었지만, 막상 실제로 진행해보니 어느정도까지 해야하는지 감을 잡기도 어려웠고, 처음 설계대로 흘러가지 않아 수정하기도 하는 등, 어려움이 많았던것 같습니다. 하지만 수업시간에 배운 내용들을 실제로 도입해보면서 이렇게 사용해야하는구나를 깨달아서 개인적으로 얻어간 것이 더 많았다고 생각합니다.
    
    중간에 인원공백이 생겨 시간적인 여유가 부족해 구현하지 못한 기능들도 있었고, 팀장으로서 좀 더 잘했어야 했다는 아쉬움이 남지만, 팀원분들이 각자 자발적으로 잘해주셔서 결국엔 마무리 할 수 있었던 것 같습니다. 다들 정말 감사합니다!
    
👩‍💻 최보현  
    
    DB 수정이 자주 발생하면서 초기 설계의 중요성을 몸소 느꼈습니다.
    하지만 누구 하나 맡은 역할 미루지 않고 열성적으로 임해준 팀원분들 덕분에 2주라는 짧은 기간 안에 프로젝트를 잘 마무리할 수 있었던 것 같습니다.  다들 고생많으셨고 감사하다고  말씀전하고싶네요!
