# API 명세서

- 공통
Base URL: /api
Content-Type: application/json
시간 포맷: createdAt, modifiedAt는 yyyy-MM-dd'T'HH:mm:ss (예: 2026-02-04T15:08:39)

1)일정 생성
POST /api/schedules
설명: 일정 등록

- Request Body
| 필드 | 타입 | 필수 | 제약 |
| --- | --- | --- | --- |
| title | string | Y | <= 30 |
| content | string | Y | <= 100 |
| name | string | Y | <= 10 |
| password | string | Y | <= 20 |

요청 예시
json
{
  "title": "미용실",
  "content": "11시 예약",
  "name": "손예진",
  "password": "1234"
} 

- Response
201 Created

응답 예시
json
{
  "id": 1,
  "title": "미용실",
  "content": "11시 예약",
  "name": "손예진",
  "createdAt": "2026-02-04T15:30:00",
  "modifiedAt": "2026-02-04T15:30:00"
}

- Error
* 400 Bad Request: 필수값 누락/길이 초과
* 500 Internal Server Error: 서버 오류


2)일정 단건 조회
GET /api/schedules/{id}
설명: 일정 1개 조회

- Path Parameter
| 이름 | 타입 | 필수 |
| --- | --- | --- |
| id | number | Y |

- Response
200 OK

응답 예시
json
{
  "id": 2,
  "title": "미용실",
  "content": "11시 예약",
  "name": "손예진",
  "createdAt": "2026-02-04T15:30:00",
  "modifiedAt": "2026-02-04T15:30:00"
}

- Error
* 404 Not Found: 해당 id의 일정이 없음


3)일정 전체 조회
GET /api/schedules
설명: 일정 전체 조회

- Response
200 OK

응답 예시
json
[
 {
   "id": 2,
   "title": "미용실",
   "content": "11시 예약",
   "name": "손예진",
   "createdAt": "2026-02-04T15:30:00",
   "modifiedAt": "2026-02-04T15:30:00"
 }
]


4)일정 수정
PUT /api/schedules/{id}
설명: 일정 1개 수정
※ 수정 시 본인 확인을 위해 password를 반드시 포함해야 함

- Path Parameter
| 이름 | 타입 | 필수 |
| --- | --- | --- |
| id | number | Y |

- Request Body
| 필드 | 타입 | 필수 | 제약 |
| --- | --- | --- | --- |
| title | string | Y | <= 30 |
| content | string | Y | <= 100 |
| name | string | Y | <= 10 |
| password | string | Y | <= 20 |

요청 예시
json
{
  "title": "방송국",
  "content": "11시 예약",
  "name": "손예진",
  "password": "1234"
} 

- Response
200 OK

응답 예시
json
{
  "id": 1,
  "title": "방송국",
  "content": "11시 예약",
  "name": "손예진",
  "createdAt": "2026-02-04T15:30:00",
  "modifiedAt": "2026-02-04T16:00:00"
}

- Error
* 400 Bad Request: 필수값 누락/길이 초과
* 403 Forbidden: 비밀번호 불일치
* 404 Not Found: 해당 id의 일정이 없음
* 500 Internal Server Error: 서버 오류


5)일정 삭제
DELETE /api/schedules/{id}?password={password}
설명: 일정 1개 삭제
※ 삭제 시 본인 확인을 위해 password를 반드시 포함해야 함

- Response
204 No Content

- Error
* 404 Not Found: 해당 id의 일정이 없음
* 403 Forbidden: 비밀번호 불일치
