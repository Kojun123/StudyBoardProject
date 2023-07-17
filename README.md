
# BoardProject
웹 게시판의 작성에 필요한 기술을 공부하고자 하여 직접 만들어본 첫 프로젝트 입니다. <br/><br/><br/>
https://boardproject.shop/  해당 링크를 클릭하시면 홈페이지를 보실 수 있습니다.  <br/><br/><br/>
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

  - <h3><b>[게스트 상태]</b></h3>
  ![게시글전체조회-게스트](https://user-images.githubusercontent.com/124665643/218430938-ab3c0fce-10ba-449f-9c8a-dba09cc57325.jpg) <br/><br/><br/>
  
  - <h3><b>[로그인 상태]</b></h3>
  ![게시글전체조회-로그인상태](https://user-images.githubusercontent.com/124665643/218430471-e3165c47-5edf-4203-8642-5ab660e781f6.PNG)<br/><br/><br/>
로그인, 로그아웃 상태에 따라서 홈페이지 오른쪽 상단의 기능들이 변하게 됩니다.<br>
게시글은 최신 작성글 기준으로 정렬됩니다. 제목 옆에는 게시글 내의 작성된 댓글의 개수를 표시하며 조회수는 쿠키를 대조하여 최초 조회시에만 카운트됩니다.
<br/><br/><br/>
  </details>
  
  <details>
  <summary><h2><strong>[글 상세조회]</strong></h2></summary>
    
   - <h3><b>[게스트(혹은 권한이 없는) 상태]</b></h3>
   <img src="https://user-images.githubusercontent.com/124665643/218439177-e04060f8-3526-4a1f-bb8c-38cd0eda5987.PNG"> <br><br>
    
   - <h3><b>[로그인 상태]</b></h3>
   <img src="https://user-images.githubusercontent.com/124665643/218439187-1a851006-3cb8-41d6-aaa4-605d6ddfd9a7.PNG"> <br><br>
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
    <img src = "https://user-images.githubusercontent.com/124665643/218444018-0afc8395-578a-48fd-b29c-52c3cb155d00.png"> <br>
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
    <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/19166be6-d950-4a50-90c3-f86c94da757f.PNG"> <br>
    회원정보 화면에서 ID, 비밀번호 변경, 가입일, 작성글 검색을 보여줍니다. <br>
    <h3><b>[작성글 검색 화면]</b></h3>
    <img src = "https://github.com/Kojun123/MyProject01/assets/124665643/091798f3-ebd6-420a-86d7-40f8d5bab8a9.PNG"> <br>
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
<h4>스프링부트를 독학하며 이런 기능이 있다는 것은 알겠으나 어떻게 사용해야 좋을지 잘 모르겠어서 직접 구현하며 고민하고<br><br>
이해하기 위해 처음으로 제대로 만들어 본 프로젝트이며 혼자서 제작하기에 막막할 때도 많았습니다.<br><br>
그러나 고민이 해결되었을때는 즐겁고 큰 성취감을 느꼈습니다, 이러한 과정을 통해 코딩에 더욱 매력을 느끼게 되었습니다.<br><br>
초기에 구상한 내용은 공부한 내용들을 사용한 간단한 CRUD 기능이 있는 게시판 이었으나,<br><br>
제작하며 어떻게 해야 더 나은 게시판을 만들 수 있을까 고민 하며<br><br>
혼자서 댓글과 회원가입, 로그인, 게시판 검색과 같은 기능들을 처음으로 만들어보는 과정은 큰 공부가 되었습니다.<br><br>
공부한 내용들을 직접 코드로 작성하는 과정에서는 예상치 못한 오류에 막히는 때도 많았지만<br><br>
오류를 해결하기 위해 오류 메시지를 해석하고 해결법을 찾아보며 해결하게 된 과정은 힘들기도 하였으나, 특히 의미있었다고 생각합니다.<br><br>
그 전에는 잘 사용하지 못하였던 브레이크 포인트를 걸고 디버깅 모드를 사용하는 것을 많이 배웠습니다.<br><br>
어느 부분에서 오류가 발생하는지 탐색하고 값이 예상과 다르게 비어있거나 다르다면 그 부분의 코드를 다시 한번 확인하고 수정하는 능력을 잘 기르게 되었다고 생각합니다.<br><br><br>
웹호스팅은 aws의 lightsail을 이용하였습니다. 과거에 aws를 이용한 직무체험교육에서의 경험을 살렸습니다. <br><br>
직무교육에서 aws의 ec2를 다루는 방법을 익혔던 경험들을 살려서 조금 더 저렴한 lightsail을 사용하게 되었습니다.

프로젝트를 마치고 나서는 보람과 함께 아쉬운 부분도 느꼈습니다.<br><br>
프로젝트를 진행하며 기록을 함께 하지 못한것이 아쉽다고 생각합니다.<br><br>
프로젝트 진행을 하며 개인기록용으로 당시에는 어떤 생각을 가지고 진행하였으며 어떤 어려움을 겪게 되었고<br><br>
어떻게 해결하였는지 같은 것을 기록해 두었으면 나중에 다시 한번 읽어보며 생각이 어떻게 변했고<br><br>
지금은 그 어려움이 어떻게 느껴지는지와 같은 것을 확인할 수 있었을 것 같다고 생각합니다.<br><br>
이후에는 새롭게 배우게 되는 것들을 프로젝트에 점차적으로 추가하며 자신의 것으로 만들어보려고 합니다. <br><br>
진행 과정동안 저의 부족한 부분을 스스로 인지하게 되었고 어느 부분을 공부해야 할지 방향성을 찾을 수 있게된 것 같습니다.<br><br>
프로젝트를 마치고 나서는 자신감을 얻게 되었고 앞으로도 더 노력할 힘을 가지게 되었습니다.<br><br>

읽어주셔서 감사드립니다.<br></h4>

