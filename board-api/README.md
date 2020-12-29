# 게시판 API Server 만들기

## 테이블 구조
- TB_BOARD
```
DOC_NO int(10) not null primary key
TITLE varchar(400) not null
CONTENT varchar(4000) not null
WRITER varchar(400) not null
REG_DTTM datetime default CURRENT_TIMESTAMP
VIEW int(10) default 0
```
- TB_REPLY
```
REPLY_NO int(10) not null primary key
DOC_NO int(10) not null primary key foregin key (TB_BOARD DOC_NO)
WRITER varchar(400) not null
CONTENT varchar(4000) not null
REG_DTTM datetime default CURRENT_TIMESTAMP
```

## MySQL 테이블 생성
- TB_BOARD
```
create table `TB_BOARD` (
  `DOC_NO` int(10) not null,
  `TITLE` varchar(400) not null,
  `CONTENT` varchar(4000) not null,
  `WRITER` varchar(400) not null,
  `REG_DTTM` datetime default CURRENT_TIMESTAMP,
  `VIEW` int(10) default 0, primary key (`DOC_NO`)
);
```
- TB_REPLY
```
create table `TB_REPLY` (
  `REPLY_NO` int(10) not null,
  `DOC_NO` int(10) not null,
  `WRITER` varchar(400) not null,
  `CONTENT` varchar(4000) not null,
  `REG_DTTM` datetime default CURRENT_TIMESTAMP,
  primary key (`REPLY_NO`, `DOC_NO`),
  foreign key (`DOC_NO`) references `TB_BOARD`(`DOC_NO`)
);
```

## CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
(1) pom.xml 에 의존성 추가  
```
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <scope>test</scope>
</dependency>
```
(2) 요청 메소드 또는 클래스에 @CrossOrigin 추가

## Docker
(1) images 다운
```
docker pull 'image name'
```
(2) container 구성
```
docker run -d -p 'port' -e 'password setting' --name 'container name' 'image name' --character-set-server=utf8 --collation-server=utf8_unicode_ci
```
(3) container 실행
```
docker exec -i -t 'container name' bash
```
(4) MySQL 접속
```
mysql -u root -p 'database name'
```

## Spring Boot + Mybatis 프로젝트 구조
src/main/java/package/  
src/main/java/package/config      => 설정파일  
src/main/java/package/controller  => 컨트롤러  
src/main/java/package/domain      => VO  
src/main/java/package/repository  => DAO  
src/main/java/package/service     => Service  
  
src/main/resources/  
src/main/resources/mappers        => Mapper  

## MySQL 접속 설정
- resources/application.properties 내 작성
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver => 드라이버
spring.datasource.url=jdbc:mysql://localhost:3306/DB명       => 접속 URL
spring.datasource.username=username                          => 사용자명
spring.datasource.password=password                          => 비밀번호
```

## Log 설정
- resources/logback-spring.xml 생성 후 내용 설정  
(1) Console Appender 추가  
(2) Logger 설정 (SQL Log 출력 시 jdbc.sqlonly 추가)  
(3) Root Logger 설정  

## Auto Build 설정
- Spring Devtools 의존성 추가
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <optional>true</optional>
</dependency>
```

## MySQL 시간 동기화
(1) DB Timezone 설정 확인 (미설정일 경우 count 값이 0)
```
select count(*) from mysql.time_zone;
```
(2) Timezone 설정을 위해 명령어 입력
```
# mysql_tzinfo_to_sql /usr/share/zoneinfo | mysql -u root -p mysql
```
(3) DB Timezone 재확인 (기본설정일 경우 SYSTEM 으로 표시)
```
select @@global.time_zone, @@session.time_zone;
```
(4) Timezone Seoul로 변경
```
set global time_zone = 'Asia/Seoul';
set time_zone = 'Asia/Seoul';
```
(5) (3) 쿼리 이용 Timezone Seoul로 변경 확인  
(6) 현재시간 확인
```
select now();
```

## mvn 명령어
- 패키지 clean & build, 테스트 제외
```
./mvnw clean package -DskipTests
```

## MySQL DataBase
- DB 생성
```
create database 'database name' default character set utf8;
```
- DB 확인
```
show databases;
```
- DB 사용
```
use 'database name';
```

## MySQL User
- User 생성 (% 는 외부 접근 허용)
```
create user 'user id'@'%' identified by 'password';
```
- User 삭제
```
drop user 'user id'@'%';
```
- 모든 DB, 테이블 권한 부여
```
grant all privileges on *.* to 'user id'@'%';
```
- 권한 적용
```
flush privileges;
```
- 권한 확인
```
show grants for 'user id'@'%';
```