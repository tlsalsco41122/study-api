# Study API

## Requirements
- JDK 21
- Gradle (wrapper included)
- Docker (optional)

## Run (local)
```zsh
./gradlew bootRun
```

## Run tests
```zsh
./gradlew test
```

## H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:studydb`
- Username: `sa`
- Password: (empty)

## Sample Users
- owner / owner1234
- member / member1234
- pending / pending1234

## Docker
```zsh
docker compose up --build
```

## API (summary)
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

