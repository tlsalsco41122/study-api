# Study API

## H2 콘솔
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:studydb`
- Username: `sa`
- Password: (비어 있음)

## 샘플 사용자
- owner / owner1234
- member / member1234
- pending / pending1234

## Docker
```zsh
docker compose up --build
```

## API 요약
- Auth: `POST /api/auth/signUp`, `POST /api/auth/signIn`
- Token: `POST /api/token`
- Studies
  - `GET /api/studies`
  - `GET /api/studies/{studyId}`
  - `POST /api/studies`
  - `PUT /api/studies/{studyId}`
  - `DELETE /api/studies/{studyId}`
- Study Members
  - `POST /api/studies/{studyId}/members` (apply)
  - `PUT /api/studies/{studyId}/members/{userId}` (approve/reject)
  - `DELETE /api/studies/{studyId}/members/me` (leave)

