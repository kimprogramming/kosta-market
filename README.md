# Kosta Market

## 프로젝트 개요 및 목적
**Kosta-Market** 은 **Spring Boot + MyBatis** 기반으로 구현된 쇼핑몰 프로젝트입니다. <br>
이 프로젝트는 쇼핑몰 서비스에 필수로 포함되는 3가지 요소 **‘회원’**, **‘상품’**, **‘결제’** 도메인으로 <br>
핵심적인 기능을 개발하고 서비스를 구축해보는 것에 목적을 두고 있습니다. 

<br>

## 팀 구성 및 역할
  - 장현준
    - 회원 도메인 담당
  - 김정열
    - 상품 도메인 담당
    - 웹페이지(상품)
  - 김태훈
    - 주문 도메인 담당
    - 유스케이스 작성
  - 정완규
    - DB 설계 및 구축
    - 웹페이지(전체)
    - 프로젝트 관리
      - 개발프로세스 정립
      - API 설계 문서 관리
      - Issue 관리
<br>

## 프로젝트 구조
### 1) E-R Diagram
![image](https://user-images.githubusercontent.com/35141349/187655577-0d01cd59-7bba-44e7-8422-702a6bddac25.png)

### 2) Class Diagram
- image Here

### 3) Usecase Diagram
![image](https://user-images.githubusercontent.com/35141349/188361961-cb2a205c-fe8a-416b-b3af-b6899669a981.png)

### 4) 프로젝트 패키지 구조 
<pre>
kosta-market
    └─ src
        └─ main
            ├─ java
            │   └─ kosta
            │       └─ market
            │           ├─ domain
            │           │   ├─ user
            │           │   │   ├─ controller
            │           │   │   ├─ service 
            │           │   │   └─ model 
            │           │   ├─ product
            │           │   │   ├─ controller
            │           │   │   ├─ service 
            │           │   │   └─ model 
            │           │   └─ order
            │           │       ├─ controller
            │           │       ├─ service 
            │           │       └─ model 
            │           └─ global
            │               ├─ error
            │               │   └─ exception
            │               └─ util
            └── resources
                 ├─ static
                 │   ├─ css
                 │   ├─ fonts
                 │   ├─ img
                 │   ├─ js
                 │   ├─ sass
                 │   └─ webfonts
                 ├─ data.sql
                 ├─ init.sql
                 └─ application.yml
</pre>

<br>

## API 설계 원칙 
* Resource 에 대한 행위는 **HTTP Method** 로 표현한다.
* URI 경로는 **소문자를 사용**하고, **Under Score('_')를 사용하지 않는다.**
* 특정 조건에 따라 가져오는 데이터의 양이 다르고 그 형태는 동일해야한다면 **"Request Param"** 을 이용한다.
* 고유한 자원 하나를 지정할 때는 **"Path Variable"** 을 이용한다.
* HTTP Method 와 URI 만으로 해당 API 기능을 유추하기 어렵다면 **예외적으로 동사를 붙혀 표현한다.**
* 동일한 HTTP Method + URL 에서는 반드시 동일한 형태의 데이터를 받아온다. 단, 특정 항목의 생략은 허용한다.
* 받아와야 할 데이터 형태가 다르다면 URI 경로를 달리해서 구분한다. 

<br>

## API 기본 규격

### 1) 데이터 표현규격
 * JSON : 데이터를 교환하기 위한 API의 메시지 형식은 JSON 방식을 사용함
> JSON (JavaScript Object Notation) <br>
> : 용량이 적은 메시지를 송수신하기 위해 데이터 객체를 속성·값(Key:Value) 형식으로 표현하는 개방형 표준 메시지 형식

<br>

 * 메시지 인코딩 방식 : 메시지 전송을 위한 인코딩 방식은 UTF-8 을 사용함
> UTF-8 <br>
> : ASCII 코드를 확장하여 전 세계의 모든 문자코드를 표현할 수 있는 표준 인코딩 방식으로써, 범용성이 높아 호환성이 우수

 * 명명 규칙 : API 명세 내 URI와 파라미터의 명명으로 'Under Scores'표기법(각 소문자 영단어가 밑줄 문자(_)로 연결)을 사용함
 
 * 시간 표현 형식 : RFC3339 표준규격을 사용함<br>
<pre>예시) 2099-01-01</pre>

<br>
 
### 2) 데이터 통신규격
 
 * 메시지 교환 방식 : API 요청 및 응답(메시지) 교환방식은 REST 방식을 사용함
> REST (REpresentational State Transfer) <br>
> : HTTP 기반으로 데이터를 전달하는 프레임워크로써, URI로 정보의 자원을 표현하고 자원에 대한 행위를 HTTP 메소드(GET, POST 등)로 표현 <br>
>   다만, 본 프로젝트는 REST 방식을 도입했을 뿐 RESTful API 설계 규칙과는 어긋나는 부분이 있음
<br>

 * URI 계층 구조 : 도메인별 웹서버 상의 자원을 고유하게 식별하고, 위치를 지정할 수 있도록 URI 계층 구조는 <br>
 <code> (base path) / (domain) / (resource)</code>  형태로 구성됨
> (base path) : API 서버의 도메인명 또는 IP, 로컬 실행 시 "http://127.0.0.1" <br>
> (domain) : 도메인 정보, 회원(=user), 상품(=product), 결제(=order) 중 하나가 지정됨 <br>
> (resource) : 자원에 대한 고유한 값

<pre>예시) "회원 로그인을 시도하는 API" 인 경우

http://127.0.0.1/<b>user</b>/signin
</pre>


## API 목록
<details>
<summary> <h3> 회원 도메인 </h3> </summary>




<details>
<summary> <h4> [회원] 로그인 </h4> </summary>
 아이디, 패스워드를 입력하여 사용자 계정에 접근합니다. <br><br>

<pre><b>POST</b> /api/user/signin HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Request Body </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| username | String | 사용자 계정 ID         |  O       |
| password | String | 사용자 계정 PW         |  O       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>





<details>
<summary> <h4> [회원] 회원가입 </h4> </summary>
 아이디, 패스워드, 이름, 전화번호를 입력받아 새로운 계정을 생성합니다. <br><br>

<pre><b>POST</b> /api/user/signup HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Request Body </h5>
  
| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| username | String | 사용자 계정 ID           |  O       |
| password | String | 사용자 계정 PW           |  O       |
| name     | String | 이름                    |  O       |
| contact  | String | 전화번호                |  O       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>





<details>
<summary> <h4> [회원] 회원정보 불러오기 </h4> </summary>
 접속한 계정의 회원정보를 불러옵니다. <br><br>

<pre><b>GET</b> /api/user HTTP/1.1</pre>

<h4> Request </h4>
- No Request Param & Body

<br>

<h4> Response </h4>
<pre>HTTP/1.1 200 OK</pre>

| Name     | Type   | Description           |
| -------- | ------ | --------------------- |
| userId   | String | 사용자 계정 고유 식별번호   |  
| username | String | 사용자 계정 ID           |
| name     | String | 사용자 이름              |
| contact  | String | 전화번호                |
| sellerId  | String | 판매자 등록 고유번호      |
  
<br><br>
</details>



<details>
<summary> <h4> [회원] 회원정보 변경 </h4> </summary>
 입력한 정보로 회원정보를 변경합니다. <br><br>

<pre><b>PUT</b> /api/user HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>  

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| password | String | 사용자 계정 PW           |  O       |
| contact  | String | 전화번호                |  O       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>




  
<details>
<summary> <h4> [회원] 회원탈퇴 </h4> </summary>
 비밀번호가 일치하면 사용자 계정을 삭제합니다. <br><br>

<pre><b>DELETE</b> /api/user HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| password | String | 사용자 계정 PW           |  O       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>





<details>
<summary> <h4> [회원] 배송지 등록 </h4> </summary>
 새로운 배송지 정보를 등록합니다. <br><br>

<pre><b>POST</b> /api/user/address HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| title | String | 배송지명           |  O       |
| deliveryPlace | String | 배송지 주소           |  O       |
| recipient | String | 상품 수령인           |  O       |
| contact  | String | 전화번호                |  O       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>



  
  
<details>
<summary> <h4> [회원] 배송지 불러오기 </h4> </summary>
 배송지 정보를 가져옵니다. <br><br>

<pre><b>GET</b> /api/user/address HTTP/1.1</pre>

<h4> Request </h4>
- No Request Param & Body

<br>
<h4> Response </h4>
<pre>HTTP/1.1 200 OK</pre>

| Name     | Type   | Description           |
| -------- | ------ | --------------------- |
| addressId  | Integer | 배송지 고유 식별번호    |
| userId  | Integer | 사용자 계정 고유 식별번호    |
| title | String | 배송지명           |
| deliveryPlace | String | 배송지 주소           |
| recipient | String | 상품 수령인           | 
| isDefaultAddress | String | 기본 배송지 여부 (Y/N) |
| contact  | String | 전화번호                |
<br><br>
</details>





<details>
<summary> <h4> [회원] 배송지 삭제 </h4> </summary>
 선택한 배송지 정보를 삭제합니다. <br><br>

<pre><b>DELETE</b> /api/user/address HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>
  
| Name     | Type   | Description           |
| -------- | ------ | --------------------- |
| addressId  | Integer | 배송지 고유 식별번호    |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>



  
  
<details>
<summary> <h4> [회원] 판매자 등록 </h4> </summary>
 상품 판매 권한을 취득하기 위해 판매자 정보를 등록합니다. <br><br>

<pre><b>POST</b> /api/user/seller HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>
  
| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | --------- | 
| businessRegNo  | String | 사업자 등록번호    | O |

<br>  
<h4> Response </h4>
<pre>HTTP/1.1 200 OK</pre>
<br><br>
</details>

  
  


<details>
<summary> <h4> [회원] 판매자 권한 제거 </h4> </summary>
 등록된 판매자 정보를 제거합니다. <br><br>

<pre><b>DELETE</b> /api/user/seller HTTP/1.1</pre>

<h4> Request </h4>
<h5> - Request Body </h5>
  
| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | ---------- |
| businessRegNo  | String | 사업자 등록번호    | O |

<br>
<h4> Response </h4>
<pre>HTTP/1.1 200 OK</pre>
</details>
<br><br>
</details>





<details>
<summary> <h3> 상품 도메인 </h3> </summary>

<details>
<summary> <h4> [상품] 상품 등록 </h4> </summary>
 새로운 상품 정보를 등록합니다. <br><br>

<pre><b>POST</b> /api/product/ HTTP/1.1</pre>

<br>
<h4> Request </h4>

<h5> -RequestPart </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| productName | String | 상품명           |  O       |
| productPrice | Integer | 상품가격            |  O       |
| productImgFileName | String | 상품이미지파일명           |  O       |
| productImgPath | String | 상품이미지파일경로           |  O       |
| productDescription | String | 상품상세설명                |  O       |
| productQuantity | Integer | 상품수량                |  O       |
| categoryId  | Integer | 상품카테고리번호                |  O       |
| imgFile  | MultipartFile | 상품이미지파일<br>초기값: default.jpg  |  X       |
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>
<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>



  
  
<details>
<summary> <h4> [상품] 상품 리스트 불러오기 </h4> </summary>
 상품 리스트를 가져옵니다. <br><br>

<pre><b>GET</b> /api/product HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -RequestParam </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| categoryId  | Integer | 카테고리 식별 번호<br>초기값 : 0  |  X |
| userType  | String | 사용자 타입<br>초기값 : "BUYER"  | X |
| search | String | 상품검색명  | X |
| sortBy | String | 상품 정렬 기준(초기값:"default") | X |
| size | Integer | 한 페이지 내에 보여주는 상품 개수<br>초기값 : 10 | X | 
| page | Integer | 현재 페이지<br>초기값 : 1 | X | 

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| score | Integer | 평균 평점  |  
| productName | String | 상품명 | 
| productPrice | Integer | 상품가격 |
| productImgFileName | String | 상품이미지파일명 |
| productImgPath | String | 상품이미지파일경로 |
| productDescription | String | 상품상세설명 |
| productQuantity | Integer | 상품수량 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>



<details>
<summary> <h4> [상품] 상품 상세 정보 조회 </h4> </summary>
 선택한 상품 정보를 상세 조회합니다. <br><br>

<pre><b>GET</b> /api/product/{product_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id  | Integer | 상품 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| productId | Integer | 상품 식별 번호 |
| productName | String | 상품명 | 
| productPrice | Integer | 상품가격 |
| productImgFileName | String | 상품이미지파일명 |
| productImgPath | String | 상품이미지파일경로 |
| productDescription | String | 상품상세설명 |
| productQuantity | Integer | 상품수량 |
| categoryId | Integer | 카테고리 식별 번호 |
| categoryName | String | 카테고리 이름 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>





<details>
<summary> <h4> [상품] 상품 정보 수정 </h4> </summary>
 선택한 상품 정보를 수정합니다. <br><br>

<pre><b>PATCH</b> /api/product/{product_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id  | Integer | 상품 식별 번호 | O |

<h5> -RequestPart </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| productName | String | 상품명           |  O       |
| productPrice | Integer | 상품가격            |  O       |
| productImgFileName | String | 상품이미지파일명           |  O       |
| productImgPath | String | 상품이미지파일경로           |  O       |
| productDescription | String | 상품상세설명                |  O       |
| productQuantity | Integer | 상품수량                |  O       |
| categoryId  | Integer | 상품카테고리번호                |  O       |
| imgFile  | MultipartFile | 상품이미지파일<br>초기값: 기존이미지파일  |  X       |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
<details>
<summary> <h4> [상품] 상품 삭제 </h4> </summary>
 선택한 상품을 삭제합니다. <br><br>

<pre><b>DELETE</b> /api/product/{product_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id  | Integer | 상품 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>


<details>
<summary> <h4> [상품] 상품 이미지 불러오기 </h4> </summary>
 상품의 이미지를 출력합니다. <br><br>

<pre><b>GET</b> /api/product/img HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -RequestParam </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| productImgFileName | String | 상품 파일 이미지 이름 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>
이미지 반환

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>   

<details> 
<summary> <h4> [상품] 카테고리 리스트 불러오기 </h4> </summary>
 상품 등록 시 필요한 카테고리 목록을 출력합니다. <br><br>
<pre><b>GET</b> /api/category HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> - URL Only

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| categoryId | Integer | 카테고리 식별 번호 |
| categoryName | String | 카테고리 이름 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>  


<details>
<summary> <h4> [상품] 장바구니 등록 </h4> </summary>
 장바구니를 등록합니다. <br><br>

<pre><b>POST</b> /api/product/cart HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -RequestBody </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| userId | Integer | 사용자 식별 번호 | O |
| productId | Integer | 상품 식별 번호 | O |
| quantity | Integer | 장바구니에 담은 상품 수량 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>


<details>
<summary> <h4> [상품] 장바구니 수정 </h4> </summary>
 장바구니 목록에서 장바구니에 담은 상품 수량을 수정합니다. <br><br>

<pre><b>PATCH</b> /api/product/cart HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -RequestBody </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| userId | Integer | 사용자 식별 번호 | O |
| productId | Integer | 상품 식별 번호 | O |
| quantity | Integer | 장바구니에 담은 상품 수량 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>       


<details>
<summary> <h4> [상품] 장바구니 삭제 </h4> </summary>
 장바구니 목록에서 일부 목록을 삭제합니다. <br><br>

<pre><b>DELETE</b> /api/product/cart HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -RequestBody </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| userId | Integer | 사용자 식별 번호 | O |
| productId | Integer | 상품 식별 번호 | O |
| quantity | Integer | 장바구니에 담은 상품 수량 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>       


<details>
<summary> <h4> [상품] 댓글 등록 </h4> </summary>
 상품 구매 후 댓글을 등록합니다. <br><br>

<pre><b>POST</b> /api/product/{product_id}/comment HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |

<h5> -RequestBody </h5>
| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| orderId | Integer | 주문 식별 번호 | O |
| productId | Integer | 상품 식별 번호 | O |
| score | Integer | 평점 | O |
| content | String | 댓글내용 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>


<details>
<summary> <h4> [상품] 댓글 상세 정보 불러오기 </h4> </summary>
 댓글에 대한 상세 정보를 불러옵니다. <br><br>

<pre><b>GET</b> /api/product/{product_id}/comment/{comment_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |
| comment_id | Integer | 댓글 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| commentId | Integer | 댓글 식별 번호 |
| orderId | Integer | 주문 식별 번호 | 
| productId | Integer | 상품 식별 번호 |
| score | Integer | 평점 |
| content | String | 댓글 내용 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>      


<details>
<summary> <h4> [상품] 댓글 수정 </h4> </summary>
 사용자가 작성한 댓글 내용을 수정합니다. <br><br>

<pre><b>PATCH</b> /api/product/{product_id}/comment/{comment_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |
| comment_id | Integer | 댓글 식별 번호 | O |

<h5> -RequestBody </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| commentId | Integer | 댓글 식별 번호 | O |
| orderId | Integer | 주문 식별 번호 | O |
| productId | Integer | 상품 식별 번호 | O |
| score | Integer | 평점 | O |
| content | String | 댓글 내용 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>       


<details>
<summary> <h4> [상품] 댓글 삭제 </h4> </summary>
 사용자가 작성한 댓글을 삭제합니다. <br><br>

<pre><b>DELETE</b> /api/product/{product_id}/comment/{comment_id} HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |
| comment_id | Integer | 댓글 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>       


<details>
<summary> <h4> [상품] 댓글 리스트 불러오기 </h4> </summary>
 상품 상세 조회 시 상품과 관련된 댓글 목록을 불러옵니다. <br><br>

<pre><b>GET</b> /api/product/{product_id}/comment/ HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |

<h5> -RequestParam </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| page | Integer | 상품 식별 번호<br> 초기값 : 0 | O |
| size | Integer | 댓글 식별 번호<br> 초기값 : 10 | O |
| userId | Integer | 사용자 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| commentId | Integer | 댓글 식별 번호 |
| orderId | Integer | 주문 식별 번호 | 
| productId | Integer | 상품 식별 번호 |
| score | Integer | 평점 |
| content | String | 댓글 내용 |
| name | String | 유저 이름 |
| createdAt | String | 댓글 작성 시간 (Unix timestamp, 13 digits)|

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>   



<details>
<summary> <h4> [상품] 상품에 달린 개수 불러오기 </h4> </summary>
 상품에 관련된 총 댓글 개수를 불러옵니다. <br><br>

<pre><b>GET</b> /api/product/{product_id}/comment/count HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> -PathVariable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| product_id | Integer | 상품 식별 번호 | O |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| commentCount | Integer | 댓글 개수 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>   




<details>
<summary> <h4> [상품] 상품 개수 불러오기 </h4> </summary>
 카테고리별 등 조건에 따른 상품 총 개수를 불러옵니다. <br><br>

<pre><b>GET</b> /api/product/count HTTP/1.1</pre>
<br>
<h4> Request </h4>
<h5> - RequestParam </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| categoryId | Integer | 카테고리 식별 번호<br> 초기값 : 0 | X |
| userType | String | 사용자 유형 <br> 초기값 : "BUYER" | X |
| search | String | 상품 검색명 | X |

<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| productCount | Integer | 상품 개수 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>   
  
  
</details>













<details>
<summary> <h3> 결제 도메인 </h3> </summary>


<details>
<summary> <h4> [결제] 상품 주문하기 </h4> </summary>
상품 고유 식별번호와 주문 수량을 전달받아 상품을 주문합니다. <br><br>

<pre><b>POST</b> /api/order HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Query Param </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| productList | String | <b>상품 고유 식별번호</b> 및 <b>주문 수량</b>이 포함된<br> 문자열 리스트로 아래와 같은 형태 <br><br>(상품):(수량), ... 형태<br><br> 예) ?productList=13:3,14:2          |  <center> O </center> |

  
<h5> - Request Body </h5>

  | Name          | Type    | Description           | Mandatory |
  | --------      | ------  | --------------------- | --------- |
  | addressId     | Integer | 배송지 고유 식별번호       |  <center>O</center>  |
  | paymentMethod | String  | 결제 방법 <br> 휴대폰 : "PHONE", 카드 : "CARD", 계좌이체 "CREDIT" 를 식별하는 값 | O |

<br>
<h4> Response </h4>
<pre>HTTP/1.1 200 OK</pre>
  
<br><br>
</details>
  
  
  
  
  
<details>
<summary> <h4> [결제] 주문 리스트 불러오기 (일반 사용자) </h4> </summary>
 일반 사용자 또는 판매자를 구분하여, 상품 구매내역 또는 상품 판매내역을 불러옵니다. <br><br>

<pre><b>GET</b> /api/order/buyer HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - URL Only </h5>

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| orderId | Integer | 주문 고유 식별번호  |  
| orderDate | String | 주문 시간 (Unix timestamp, 13 digits) |
| paymentPrice | Integer | 결제 금액 |
| paymentMethod | String | 결제 방법 |
| deliveryPlace | String | 배송지 주소 |
| contact | String | 수령인 연락처 |
| recipient | String | 수령인 |
| orderList | Array | 주문한 상품 목록 |
| orderList.productId | Integer | 상품 고유 식별변호 |
| orderList.orderQuantity | Integer | 주문 수량 |
| orderList.orderPrice | Integer | 결제 금액 |
| orderList.productName | String | 상품명 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  


<details>
<summary> <h4> [결제] 주문 리스트 불러오기 (판매자) </h4> </summary>
 일반 사용자 또는 판매자를 구분하여, 상품 구매내역 또는 상품 판매내역을 불러옵니다. <br><br>

<pre><b>GET</b> /api/order/seller HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - URL Only </h5>

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| orderId | Integer | 주문 고유 식별번호  |  
| orderDate | String | 주문 시간 (Unix timestamp, 13 digits) |
| orderState | String | 주문 상태 |
| orderPrice | Integer | 결제 금액 |
| orderQuantity | Integer | 주문 수량 |
| productId | Integer | 상품 고유 식별변호 |
| productName | String | 상품명 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
  
  
  
<details>
<summary> <h4> [결제] 주문 상세정보 </h4> </summary>
 특정 주문서의 상세 내역을 가져옵니다. <br><br>

<pre><b>GET</b> /api/order/{orderId} HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Path Variable </h5>

| Name     | Type   | Description           | Mandatory |
| -------- | ------ | --------------------- | -------- |
| orderId | Integer | 주문 고유 식별번호 | <center> O </center>      |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

| Name     | Type   | Description           | 
| -------- | ------ | --------------------- | 
| orderId | Integer | 주문 고유 식별번호  |  
| orderDate | String | 주문 시간 (Unix timestamp, 13 digits) |
| orderList | Array | 주문한 상품 목록 |
| orderList.productId | Integer | 상품 고유 식별변호 |
| orderList.orderQuantity | Integer | 주문 수량 |
| orderList.orderState | String | 주문 상태 |
| orderList.orderPrice | Integer | 결제 금액 |
| orderList.productName | String | 상품명 |
| paymentPrice | Integer | 결제 금액 |
| paymentMethod | String | 결제 방법 |
| deliveryPlace | String | 배송지 주소 |
| contact | String | 수령인 연락처 |
| recipient | String | 수령인 |

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
  
  
  
<details>
<summary> <h4> [결제] 상품 교환신청 </h4> </summary>
 결제한 상품의 주문 상태를 "교환신청" 으로 변경합니다.  <br><br>

<pre><b>PATCH</b> /api/order/{orderId}/exchange HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Query Param </h5>

| Name     | Type    | Description    | Mandatory |
| -------- | ------- | -------------- | -------- |
| orderId  | Integer | 주문 고유 식별번호  |  <center> O </center>  |

<h5> - Request Param </h5>

| Name      | Type    | Description    | Mandatory |
| --------- | ------- | -------------- | -------- |
| productId | Integer | 상품 고유 식별번호  | <center> O </center>    |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
  
  
  
<details>
<summary> <h4> [결제] 주문 취소/환불  </h4> </summary>
 결제한 상품의 주문 상태를 "취소/환불" 으로 변경합니다.  <br><br>

<pre><b>PATCH</b> /api/order/{orderId}/cancel HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Query Param </h5>

| Name     | Type    | Description    | Mandatory |
| -------- | ------- | -------------- | -------- |
| orderId  | Integer | 주문 고유 식별번호  |  <center> O </center>  |

<h5> - Request Param </h5>

| Name      | Type    | Description    | Mandatory |
| --------- | ------- | -------------- | -------- |
| productId | Integer | 상품 고유 식별번호  |  <center> O </center>      |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
  
  
  
<details>
<summary> <h4> [결제] 상품 구매확정 </h4> </summary>
 결제한 상품의 주문 상태를 "구매확정" 으로 변경합니다.  <br><br>

<pre><b>PATCH</b> /api/order/{orderId}/confirm HTTP/1.1</pre>

<br>
<h4> Request </h4>
<h5> - Query Param </h5>

| Name     | Type    | Description    | Mandatory |
| -------- | ------- | -------------- | -------- |
| orderId  | Integer | 주문 고유 식별번호  |   <center> O </center>   |

<h5> - Request Param </h5>

| Name      | Type    | Description    | Mandatory |
| --------- | ------- | -------------- | -------- |
| productId | Integer | 상품 고유 식별번호  |  <center> O </center>       |

<br>
<h4> Response </h4>
<h5> - 성공 시 </h5>
<pre>HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
</pre>

<h5> - 실패 시 </h5>
<pre>HTTP/1.1 404 Not Found</pre>
<br><br>
</details>
  
  
  
  

</details>


<br>

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
            <td rowspan="3">공통</td>
            <td>버전 관리 도구</td>
            <td>Git</td>
            <td>-</td>
            <td>팀 구성원에가 가장 익숙한 기술임</td>
        </tr>
        <tr>
            <td>이슈 관리 도구</td>
            <td>Github Issue</td>
            <td> - </td>
            <td>팀 구성원이 Confluence, JIRA 가 익숙하지 않고, 빠르게 적응 가능한 Github Issue 및 Projects 를 사용함 </td>
        </tr>
        <tr>
            <td>사용자 인증 방식</td>
            <td>Session Cookie</td>
            <td>-</td>
            <td>JWT 방식은 쿠키 토큰 탈취 시 Attacker 가 Victim 의 권한으로 인증이 가능한 문제가 있고, 서버에서 즉시 인증만료 설정을 할 수 없음</td>
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
            <td>JQuery</td>
            <td>3.6.x</td>
            <td> - </td>
        </tr>
        <tr>
            <td rowspan="7"> 백엔드 </td>
            <td>의존성 관리 도구</td>
            <td>Gradle</td>
            <td>7.x</td>
            <td>Groovy 언어를 사용하기에 Java 개발자가 읽기 편하고, maven 보다 빌드 속도가 더 빠름</td>
        </tr>
        <tr>
            <td>프로그래밍 언어</td>
            <td>Java</td>
            <td>17 (Stable)</td>
            <td>현 시점으로부터 9년 간 지원 받을 수 있고, OpenJDK 8 보다 6년 더 지원 받을 수 있음 </td>
        </tr>
        <tr>
            <td>웹 프레임워크</td>
            <td>Spring Boot</td>
            <td>2.6.7 (Stable)</td>
            <td>개발 시작 기준 가장 최신의 안정된 버전임</td>
        </tr>
        <tr>
            <td>웹 서버</td>
            <td>Tomcat</td>
            <td>9.0.62</td>
            <td>직접적으로 수정할 사항이 없었으므로 기본 내장 버전 사용함</td>
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
            <td>팀 구성원에게 가장 익숙한 기술임</td>
        </tr>
    </tbody>
</table>

<br>
