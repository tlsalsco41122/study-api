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
  - `GET /api/study`
  - `GET /api/study/{studyId}`
  - `POST /api/study/create`
  - `PUT /api/study/{studyId}`
  - `DELETE /api/study/{studyId}`
- Study Members
  - `GET /api/study/{studyId}/members`
  - `POST /api/study/{studyId}/apply`
  - `POST /api/study/{studyId}/approve`
  - `POST /api/study/{studyId}/leave`
