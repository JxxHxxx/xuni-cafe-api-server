# xuni-cafe-api-server
로컬 환경에서 test 수행 시, docker 를 이용합니다.

<br>

* mongodb image 생성 및 실행

```
docker run -d --name {container_name} -p 27017:27017 mongo:latest
```

<br>

* container 실행

```
docker start {container-id}
```

<br>

* mongo cli 접속

```
docker exec -it {container-id} mongosh
```

<br> 

* 간단한 명령어
```
// 컬렉션 생성
db.createCollection("places") <- places 라는 컬렉션 생성

db.places.insertOne({name: "스타벅스 유니DT점"}) <- places 컬렉션에 document 추가

db.places.find() <- places 컬렉션 내 document 조회
```
