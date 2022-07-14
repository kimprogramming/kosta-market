# Getting Started

---
## 프로젝트 소개
**Kosta-Market** 은 **Spring Boot + MyBatis** 기반으로 구현되었으며, 쇼핑몰 서비스 운용 시
반드시 필요한 3가지 요소인 **회원**, **상품**, **결제** 도메인으로 핵심적인 기능을 구축하는데 목적을 두고 있습니다.
세부적인 기능은 아래와 같습니다.

<details>
<summary> <b>기능 명세서</b> </summary>

- 회원 관련
    - 회원가입
    - 로그인
    - 로그아웃
    - 회원탈퇴
- 상품 관련
    - 상품 등록
    - 상품 정보 읽기
    - 상품 수정
    - 상품 제거
- 결제 관련
    - 주문
</details>

---
## 개발인원
  - 박주현
    - Usecase Diagram 작성
  - 김정열
  - 장현준
    - Usecase Diagram 작성
  - 정완규
    - 문서 작성
---
## 동작
1. 1
2. 2
---
## 프로젝트 구조
  - 쇼핑몰 프로젝트
      - 회원 도메인
      - 상품 도메인
      - 결제 도메인
  - 리소스 템플릿
---
## 설치 방법
    - …
---
## 기술 세부 사양
<table>
    <thead>
        <center>
        <tr>
            <th width="90">분류</th>
            <th>항목</th>
            <th>기술명</th>
            <th>버전</th>        
            <th>도입근거</th>
        </tr>
        </center>
    </thead>
       <tbody>
        <tr>
            <td rowspan="4">공통</td>
            <td>버전 관리 도구</td>
            <td>Github</td>
            <td>-</td>
            <td>-</td>
        </tr>
        <tr>
            <td>이슈 관리 도구</td>
            <td>Github Issue</td>
            <td> - </td>
            <td> - </td>
        </tr>
        <tr>
            <td>사용자 인증 방식</td>
            <td>Session Cookie</td>
            <td>-</td>
            <td>-</td>
        </tr>
        <tr>
            <td>민감한 데이터 관리 방식</td>
            <td>단방향 암호화 기법 (SHA256)</td>
            <td> - </td>
            <td> - </td>
        </tr>
        <tr>
            <td rowspan="2">프론트엔드</td>
            <td>웹 페이지 구성</td>
            <td>HTML, CSS, JS</td>
            <td> - </td>
            <td> - </td>
        </tr>
        <tr>
            <td>통신 라이브러리</td>
            <td>Axios</td>
            <td>0.27.2</td>
            <td> - </td>
        </tr>
        <tr>
            <td rowspan="8"> 백엔드 </td>
            <td>동적 웹 페이지</td>
            <td>JSP</td>
            <td>-</td>
            <td>-</td>
        </tr>
        <tr>
            <td>의존성 관리 도구</td>
            <td>Gradle</td>
            <td>7.x</td>
            <td>-</td>
        </tr>
        <tr>
            <td>프로그래밍 언어</td>
            <td>Java</td>
            <td>17 (Stable)</td>
            <td>자바 8, 11 보다 나은점(근거 관련 url)</td>
        </tr>
        <tr>
            <td>웹 프레임워크</td>
            <td>Spring Boot</td>
            <td>2.6.7 (Stable)</td>
            <td></td>
        </tr>
        <tr>
            <td>웹 서버</td>
            <td>Tomcat</td>
            <td>9.0.62</td>
            <td>10버전을 쓰지 않는 이유, 차이점, 내장서버</td>
        </tr>
        <tr>
            <td>데이터베이스</td>
            <td>MySQL</td>
            <td>8.0</td>
            <td>2022년 점유율 2위(커뮤니티파워), 추가 수정 삭제가 타 DB보다 빠름</td>
        </tr>
        <tr>
            <td>SQL 매핑 프레임워크</td>
            <td>MyBatis</td>
            <td>3.5.10</td>
            <td>팀 구성원에게 가장 익숙한 기술</td>
        </tr>
    </tbody>
</table>

---
## 프로젝트 가시화
### Usecase Diagram
### E-R Diagram
### Class Diagram

- 요구사항 명세서
  [회원 테이블]
- 회원번호(PK, Auto_Increment, Int)
- 아이디(이메일)
- 비밀번호    ※ 서버에서  단방향 암호화
- 이름
- 연락처

[판매자 테이블]
- 판매자번호(PK)
- 회원번호(FK)
- 사업자번호

[상품 등록 테이블]
- 상품번호(FK)
- 회원번호(FK)

[상품 상세 테이블]
- 상품번호(PK)
- 카테고리번호(FK)
- 상품명
- 상품가격
- 상품 이미지(url) - UUID 로 파일명 변경
- 상품설명
- 재고개수

[카테고리 테이블]
- 카테고리번호(PK)
- 분류

[주문 테이블]
- 주문번호(FK)
- 회원번호(FK)

[주문 상세 테이블]
- 주문번호(PK)
- 상품번호(FK)
- 구매개수
- 주문금액
- 결제시간
- 결제수단 (카드, 무통장) 1 카드, 2 무통장, +@ 환불용
- 주문상태 (환불 관련 사용)

[주소 테이블]
- 회원번호(FK)
- 배송지
- 기본주소 여부
---
