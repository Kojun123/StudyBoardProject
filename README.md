이 프로젝트는 웹 게시판의 작성에 필요한 기술을 공부하고자 하여 직접 만들어본 Spring Boot 기반의 프로젝트 입니다. 
https://boardproject.shop/  해당 링크를 클릭하시면 홈페이지를 보실 수 있습니다. (현재는 닫음)
* * * 
# 목차
[1.프로젝트 소개](#프로젝트-소개)<br><br><br>
[2.프로젝트 기능](#프로젝트-기능)<br><br><br>
[3.사용된 기술](#사용-기술)<br><br><br>
[4.실행 화면 & 기능설명](#실행-화면--기능-설명)<br><br><br>
[5.프로젝트 구조](#구조)<br><br><br>
[6.후기](#마치며)<br><br><br>
* * *

# 프로젝트 소개

<b>명칭</b> : BoardProject<br>
<b>개발 인원</b> : 1명<br>
<b>개발 기간</b> : 약 1개월<br>
<b>한줄 소개</b> : 공부해 본 내용들을 직접 사용하며 익히기 위해 만들어본 첫 게시판 프로젝트 입니다.<br><br>
* * *
# 프로젝트 기능

<b>게시글</b> : 글 전체 조회, 상세 조회, 글 작성, 수정, 삭제, 조회수, 페이징, (제목,내용,작성자)검색<br>
<b>댓글</b> : 글 상세 조회 시 댓글 조회, 댓글 작성, 수정, 삭제<br>
<b>유저</b> : 유효성 검사 및 중복 검사 회원가입, 로그인, 회원정보(아이디 확인, 비밀번호 변경, 가입일, 작성글 검색)<br><br><br>
* * *
# 사용 기술


<b>개발 언어</b> : Java 11 <br>
<b>Data Base</b> : H2 <br>
<b>개발 환경</b> : SpringBoot 2.7.7, JPA(Spring data jpa), Spring Security, gradle, thymeleaf <br>
<b>사용 IDE</b> : Intelli J Community <br><br><br>
* * *

## 실행 화면 & 기능 설명

<details>
  
  <summary><b>펼쳐보기</b></summary>
  
  <details>
  <summary><h2><strong>[글 전체조회]</strong></h2></summary>

  - <h3><b>[로그인 상태]</b></h3>
![게시글전체조회](https://github.com/Kojun123/MyProject01/assets/124665643/a91adee5-22e2-417e-98db-ffc781ddf4d4.PNG) <br/><br/><br/>
게시글은 최신 작성글 기준으로 정렬됩니다. 제목 옆에는 게시글 내의 작성된 댓글의 개수를 표시하며 조회수는 쿠키를 대조하여 최초 조회시에만 카운트됩니다.
<br/><br/><br/>
  </details>
  
  <details>
  <summary><h2><strong>[글 상세조회]</strong></h2></summary>
    
   - <h3><b>[게스트(혹은 권한이 없는) 상태]</b></h3>
   <img src="https://github.com/Kojun123/MyProject01/assets/124665643/1cbc68ee-3ec0-4119-ac74-36795a6fba4a.PNG"> <br><br>
    
   - <h3><b>[로그인 상태]</b></h3>
   <img src="https://github.com/Kojun123/MyProject01/assets/124665643/ed9b09fb-adf1-4dfb-bbd1-a75a5dedf43a.PNG"> <br><br>
  게시글의 수정과 삭제, 댓글 작성, 수정, 삭제가 가능하며 <br>
  본인이 작성한 게시글과 댓글이 아니라면 수정/삭제가 나타나지 않으며 게스트 상태에서는 댓글 작성을 할 수 없습니다.<br>
    </details>
  
  <details>
  <summary><h2><strong>[글 작성]</strong></h2></summary>
   <h3><b>[게시글 작성 화면]</b></h3>
    <img src = "https://user-images.githubusercontent.com/124665643/218441768-09f57291-c170-4d63-8520-1a089f52ae39.PNG"> <br>
   로그인 한 유저는 게시글의 제목과 내용을 입력하여 작성이 가능합니다. 작성 후에는 목록화면으로 redirect 됩니다. <br>
  </details>
  
  <details>
    <summary><h2><strong>[글 검색]</strong></h2></summary>
    <h3><b>[게시글 검색 화면]</b></h3>
    <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/648e6462-d1cf-451b-a793-91540bf7dd3d.PNG"> <br>
    게시글은 제목, 내용, 작성자 세 가지 조건으로 검색이 가능합니다. 검색 키워드에 포함된 모든 글이 조회됩니다. 
  </details>
  
  <details>
    <summary><h2><strong>[회원가입 & 로그인]</strong>,</h2></summary>
    <h3><b>[회원가입 화면]</b></h3>
    <img src = "https://user-images.githubusercontent.com/124665643/218448047-11aa55b5-7b72-44fd-add5-091ff8feda5d.PNG"> <br>
    회원가입은 가입할 id , password, email을 입력하여 진행하며 password가 일치하지 않거나 id와 emial이 이미 존재한다면 사용자에게 다음과 같은 메시지를 보여줍니다. <br>
    <h3><b>[로그인 화면]</b></h3>
    <img src = "https://user-images.githubusercontent.com/124665643/218448897-7155703a-0f4b-418c-9f74-fe789023c366.PNG"> <br>
    로그인은 id와 password를 입력하여 진행하며 id나 password 둘 중 하나라도 일치하지 않을 시에는 다음과 같은 메시지를 보여줍니다.
  </details>

  <details>
    <summary><h2><strong>[회원정보]</strong></h2></summary>
    <h3><b>[회원정보 화면]</b></h3>
    <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/fc55d647-3849-40e6-b830-bee8c165fadd.PNG"> <br>
    회원정보 화면에서 ID, 비밀번호 변경, 가입일, 작성글 검색을 보여줍니다. <br>
    <h3><b>[작성글 검색 화면]</b></h3>
    <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/13289697-a261-4058-956d-0f826de2220a.PNG"> <br>
    작성한 게시글들을 확인할 수 있고 이동할 수 있습니다.
  </details>
  
  
</details>
</details>


# 구조


<details>
  <summary><h2><strong>[패키지 구조]</strong></h2></summary>
  <img src = "https://user-images.githubusercontent.com/124665643/218452442-b927609e-0e40-4723-9660-a7851a9087a9.PNG"> <br>
  <img src = "https://user-images.githubusercontent.com/124665643/218452446-de64d676-7a9e-45ff-9c5d-90d1b277c256.PNG"> <br>
  <img src = "https://user-images.githubusercontent.com/124665643/218452450-75d78047-bb4e-433d-a303-371b29a07b1c.PNG"> <br>
  <img src = "https://user-images.githubusercontent.com/124665643/218452452-42b60004-d6e2-4bf2-8cef-22fad0f22494.PNG"> <br>
</details><br><br><br><br>

<details>
  <summary><h2><strong>[DB 구조]</strong></h2></summary>
  
  <h3><strong>[BOARD]</strong></h3><br>
  <img src = "https://user-images.githubusercontent.com/124665643/218453470-05944fc5-38cf-40c7-9677-1a87a27e0376.PNG"> <br>
  
  <h3><strong>[COMMENT]</strong></h3><br>
  <img src = "https://user-images.githubusercontent.com/124665643/218455982-3573b45d-b856-49ad-ab18-d55b2a33b95d.PNG"> <br>

  <h3><strong>[USER]</strong></h3><br>
  <img src = "https://user-images.githubusercontent.com/124665643/218455988-d407ca10-2907-4a89-976d-21ff981ba31a.PNG"> <br>
  
  <img src = "https://user-images.githubusercontent.com/124665643/218455990-a448dd8d-6120-4d37-ad0b-e4960ae58741.PNG"> <br> 
</details><br><br><br><br><br>

<details>
  <summary><h2><strong>[API 구조]</strong></h2></summary>
  <h3><strong>[BOARD API]</strong></h3>
  <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/eef984cb-cb80-40b0-a1f3-07e1ecbdce97.PNG"> <br>
</details><br><br><br><br><br>

# [마치며..]
//가림처리

