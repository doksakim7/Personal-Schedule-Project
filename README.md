# API 명세서

- 공통
Base URL: /api
Content-Type: application/json
시간 포맷: createdAt, modifiedAt는 yyyy-MM-dd'T'HH:mm:ss (예: 2026-02-04T15:08:39)
(※ 서버 내부 오류는 공통적으로 500을 반환합니다.)

1)일정 생성
: `POST /api/schedules`
- 설명: 일정 등록

- Request Body
  
| 필드 | 타입 | 필수 | 제약 |
| --- | --- | --- | --- |
| title | string | Y | <= 30 |
| content | string | Y | <= 100 |
| name | string | Y | <= 10 |
| password | string | Y | <= 20 |

요청 예시
```json
{
  "title": "미용실",
  "content": "11시 예약",
  "name": "손예진",
  "password": "1234"
}
```

- Response: 201 Created

응답 예시
```json
{
  "id": 1,
  "title": "미용실",
  "content": "11시 예약",
  "name": "손예진",
  "createdAt": "2026-02-04T15:30:00",
  "modifiedAt": "2026-02-04T15:30:00"
}
```

- Error
1. 400 Bad Request: 필수값 누락/길이 초과


2)일정 전체 조회
: `GET /api/schedules`
: `GET /api/schedules?name={name}`
- 설명: 작성자명(name)은 선택적 조회 조건이며, 결과는 수정일 기준 내림차순 정렬

- Response: 200 OK

응답 예시
```json
[
 {
   "id": 3,
   "title": "방송국",
   "content": "10시 예약",
   "name": "김태희",
   "createdAt": "2026-02-04T16:00:00",
   "modifiedAt": "2026-02-04T16:00:00"
 },
 {
   "id": 2,
   "title": "방송국",
   "content": "14시 예약",
   "name": "손예진",
   "createdAt": "2026-02-04T15:31:00",
   "modifiedAt": "2026-02-04T15:31:00"
 },
 {
   "id": 1,
   "title": "미용실",
   "content": "11시 예약",
   "name": "손예진",
   "createdAt": "2026-02-04T15:30:00",
   "modifiedAt": "2026-02-04T15:30:00"
 }
]
```


3)일정 단건 조회
: `GET /api/schedules/{scheduleId}`
- 설명: 일정 1개 조회

- Path Parameter
  
| 이름 | 타입 | 필수 |
| --- | --- | --- |
| scheduleId | number | Y |

- Response: 200 OK

응답 예시
```json
{
  "id": 3,
  "title": "방송국",
  "content": "10시 예약",
  "name": "김태희",
  "createdAt": "2026-02-04T16:00:00",
  "modifiedAt": "2026-02-04T16:00:00"
}
```

- Error
1. 404 Not Found: 해당 일정이 존재하지 않음


4)일정 수정
: `PATCH /api/schedules/{scheduleId}`
- 설명: 일정 1개 수정
(※ title, name만 수정 가능)
(※ 수정 시 본인 확인을 위해 password를 반드시 포함해야 함)

- Path Parameter
  
| 이름 | 타입 | 필수 |
| --- | --- | --- |
| scheduleId | number | Y |

- Request Body
  
| 필드 | 타입 | 필수 | 제약 |
| --- | --- | --- | --- |
| title | string | Y | <= 30 |
| name | string | Y | <= 10 |
| password | string | Y | <= 20 |

요청 예시
```json
{
  "title": "방송국",
  "name": "손예진",
  "password": "1234"
}
```

- Response: 200 OK

응답 예시
```json
{
  "id": 1,
  "title": "방송국",
  "content": "11시 예약",
  "name": "손예진",
  "createdAt": "2026-02-04T15:30:00",
  "modifiedAt": "2026-02-04T16:15:00"
}
```

- Error 예시
1. 400 Bad Request: 필수값 누락/길이 초과
2. 403 Forbidden: 비밀번호 불일치
3. 404 Not Found: 해당 일정이 존재하지 않음


5)일정 삭제
: `DELETE /api/schedules/{scheduleId}?password={password}`
- 설명: 일정 1개 삭제
(※ 삭제 시 본인 확인을 위해 password를 반드시 포함해야 함)

- Response: 204 No Content

- Error 예시
1. 403 Forbidden: 비밀번호 불일치
2. 404 Not Found: 해당 일정이 존재하지 않음



# ERD

<img width="354" height="187" alt="스크린샷 2026-02-04 오후 3 08 39" src="https://github.com/user-attachments/assets/37574a6b-2174-437b-980d-b7698e31e55a" />
