/* 일반 사용자 계정를 생성합니다. */
INSERT INTO TBL_USER VALUES ("user1", "1111", "완규", "코스타 빌딩, 403");
INSERT INTO TBL_USER VALUES ("user2", "1111", "태훈", "코스타 빌딩, 402");
INSERT INTO TBL_USER VALUES ("user3", "1111", "현준", "코스타 빌딩, 401");

/* 판매자 계정을 생성합니다. */
INSERT INTO TBL_USER VALUES ("seller1", "1111", "정열", "코스타 빌딩, 404");

/* 판매 상품을 등록함 */
INSERT INTO TBL_PRODCT VALUES ("코스타 노트북", 658900, "/tmp/product", "uuid-file.png", "설명", 50);