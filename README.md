# 🌲 Forest

## 🌈 프로젝트 개요
- **프로젝트 명칭**: Forest
- **프로젝트 소개**: Forest는 취향 존중 오픈형 커뮤니티 사이트입니다. 다양한 관심사를 가진 유저들을 충족하기 위해 유저가 직접 랜드(게시판)을 생성하고, 글과 댓글을 남기면서 다른 유저들과 자유롭게 소통할 수 있는 커뮤니티 사이트를 만들기 위해 개발하게 되었습니다. Forest라는 이름은 숲에는 많은 나무가 있고, 나무에는 여러 열매가 달려있다는 점에서 착안하여 각각 랜드를 게시판, 나무를 게시글, 열매를 댓글에 대입하였고, 이 모든 것이 어우러진 곳은 숲이기에 Forest라고 이름을 지었습니다.
- **제작 기간**: 2023년 7월 27일 ~ 2023년 8월 23일
- **참여 인원**: 4명
- **역할**: 팀장
- **개발 파트**:
  - 개발 환경 설정
  - 랜드 (게시판)
  - 관리자
  - 즐겨찾기
  - 오픈 채팅
  - 후원


<br/>

|            [원준](https://github.com/WonjunS)             |            [한별](https://github.com/blackhabin)               |             [우현](https://github.com/kkwh)             |             [선아](https://github.com/seonaK)             |              
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | 
| <img src="https://avatars.githubusercontent.com/u/93713151?v=4" width=200px alt="_"/> | <img src="https://avatars.githubusercontent.com/u/126144148?v=4" width=200px alt="_"/> | <img src="https://avatars.githubusercontent.com/u/121307297?v=4" width=200px alt="_"/> | <img src="https://avatars.githubusercontent.com/u/134375418?v=4" width=200px alt="_"> | 
|                         🏆 팀장                         |                         🌱 팀원                        |                           🌵 부팀장                           |                           🌷 팀원                           |                           🌻 공룡                           |    


<br/>


## 📚 기술 스택

<div align=center> 

  <h4>BACK-END</h4>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">  

  <br>
  
  <img src="https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring%20security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">

  <br>
  
  <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/axios-000000?style=for-the-badge&logo=axios&logoColor=white">
  
  <br>
  
  <h4>FRONT-END</h4>
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  

  <br>
  
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 
  <img src="https://img.shields.io/badge/chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=white">

  <br>

  <h4>TOOLS</h4>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">

</div>

<br>

## 🏗️ ERD 설계

![Forest](https://github.com/WonjunS/Forest/assets/93713151/8e275292-759b-4300-aad5-1c02c7abb4da)

<br>

## 🔔 문제 발생 및 해결

- **GitHub**

  **상황**: 프로젝트 초반에 여러 명이 Main 브랜치 하나에서 push, pull등의 작업을 하다보니 종종 코드간에 충돌이 생기기도 하고,
  그 날 했던 작업을 커밋하여 백업해놓고 싶지만 아직 기능적으로 완성되지 않아서 Main 브랜치에는 커밋을 하지 못하는 일이 생겼습니다.
  또한, 충돌이 생기는 것이 두려워서 push를 해야되는데도 하지 못하는 일도 있었습니다.


  **해결**: 이러한 문제를 해결하기 위해 프로젝트의 repository에 작업을 위한 각자의 개인 브랜치를 하나씩 만들어 놓았습니다. 
  매일 작업한 코드는 개인 브랜치에 우선 push 해놓고 기능적으로 완성이 된 경우에 다른 팀원들과의 코드 리뷰 후에 Main 브랜치에 merge
  하는 방식으로 작업하였습니다. 가끔 충돌이 발생한 경우에는 관련 팀원들과의 코드 리뷰를 통해 충돌을 해결하였습니다.
  

- **컨벤션**

  **상황**: 아직 협업이 익숙하지 않아 어떤식으로 그리고 어디서부터 어디까지 컨벤션을 맞춰야 될지 확신이 없었습니다.
  정의한 컨벤션 또한 기존에 혼자 작업하던 방식이 아니기 때문에 다른 팀원이 구체적으로 어떤 작업을 하였는지 혼선이 있었습니다.


  **해결**: 이를 해결하기 위해 인터넷에 검색하여 보편적으로 사용되고 있는 컨벤션 규칙을 참고 후에 약간 변형하여 사용하기로 하였습니다.
  참고한 포스트에 있는 커밋 메세지 규칙을 비슷하게 따라하면서 누가 작업을 한 것인지 알아보기 쉽게 커밋 제목의 맨 앞에 각자의 이름을
  명시하기로 하였습니다.

  참고 사이트: https://cocoon1787.tistory.com/708


- **오픈채팅 구현**

  **상황**: 오픈채팅 기능을 구현하기 위해 STOMP를 사용하였는데, 개인 PC에서 테스트 할 때는 실시간으로 채팅 기능이 잘 작동하였지만,
  다른 PC와 테스트 할 때는 각자의 local에서 접속할 경우 새로고침을 하거나 새로운 채팅을 보내야만 상대가 보낸 메세지가 보인다는
  문제점이 있었습니다.

  **해결**: 이를 해결하기 위해, 우선은 localhost가 아닌 서로 같은 IP에서 오픈채팅방에 접속하는 방식으로 해결하였습니다.
  하지만 이 방법은 완전한 해결책이 아니기 때문에 향후 수정해보고 싶은 기능입니다.

<br>

## 💻 주요 기능

<!-- 메인 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/8fb6acdd-b366-4d6e-b8d4-ebcd63276aab)

![image](https://github.com/WonjunS/Forest/assets/93713151/1e109324-06e3-4c6f-b9e6-c785ef9635d8)



<!-- 랜드 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/2ba8f4a2-0c0b-4ffc-882f-11ab83bf90f6)

![image](https://github.com/WonjunS/Forest/assets/93713151/f057b055-a1a4-4139-8874-57027dc15da4)

![image](https://github.com/WonjunS/Forest/assets/93713151/51a667f0-7416-4cdb-827d-13bae401839f)

![image](https://github.com/WonjunS/Forest/assets/93713151/e2b9fb14-a4a3-4d78-a9af-8b578f88ffe4)

![image](https://github.com/WonjunS/Forest/assets/93713151/5bb01e5f-c40d-40ac-b45a-8f46cbc4269f)

![image](https://github.com/WonjunS/Forest/assets/93713151/4b9b8f19-d3ba-49a3-a7b5-265fb927f4c1)

![image](https://github.com/WonjunS/Forest/assets/93713151/b865c269-0a9e-44c0-af0b-766aa8813686)

![image](https://github.com/WonjunS/Forest/assets/93713151/757b5030-7442-4c9a-8ca7-0ec509275065)

![image](https://github.com/WonjunS/Forest/assets/93713151/572e4bf8-f15a-4970-80b5-2f27bc367ffe)

![image](https://github.com/WonjunS/Forest/assets/93713151/c2937b89-2a0d-4d6e-97ca-a4220317004e)

![image](https://github.com/WonjunS/Forest/assets/93713151/efb64f5a-0463-4b57-bf89-f5d8679adbae)



<!-- 나무 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/f29153fe-1374-43e7-8236-5fb3ef445060)

![image](https://github.com/WonjunS/Forest/assets/93713151/50e45492-22a7-445c-abbc-b2014dd3981f)

![image](https://github.com/WonjunS/Forest/assets/93713151/fb1bbeee-8ce2-4501-b232-5f34093e3c84)

![image](https://github.com/WonjunS/Forest/assets/93713151/f0363ec0-58be-4f96-99b9-86025c744d3d)



<!-- 열매 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/80641cb4-41cd-438d-8666-fe674893b7bc)

![image](https://github.com/WonjunS/Forest/assets/93713151/ac69823c-2e43-46f1-9269-1c6b556ab0ad)

![image](https://github.com/WonjunS/Forest/assets/93713151/526e5e22-a94e-45dc-905b-233cea732002)

![image](https://github.com/WonjunS/Forest/assets/93713151/5fc1e72c-f9e0-497e-b265-70b355272005)


<!-- 오픈채팅 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/416d61e5-0d2d-4086-98d4-3302308b4bee)


<!-- 후원 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/fc448c53-66f2-48fc-940e-3d6b711e2d5d)




<!-- 유저 -->

![image](https://github.com/WonjunS/Forest/assets/93713151/def730f9-83c0-4381-9c14-a7206f443a89)

![image](https://github.com/WonjunS/Forest/assets/93713151/03aa8915-c0a3-4b5a-bedd-43c974a6c747)

![image](https://github.com/WonjunS/Forest/assets/93713151/8266a4c5-3730-4d22-9eba-e6ba7fcbf839)
