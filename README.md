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
- Auth: `POST /api/auth/signUp`, `POST /api/auth/signIn` -  회원가입, 로그인
- Token: `POST /api/token` - 토큰 재발급
- Study
  - `GET /api/study` - 스터디 목록 조회
  - `GET /api/study/{studyId}` - 스터디 상세 조회
  - `POST /api/study/create` - 스터디 생성
  - `PUT /api/study/{studyId}` - 스터디 수정 (스터디장만 가능)
  - `DELETE /api/study/{studyId}` - 스터디 삭제 (스터디장만 가능)
- Study Member
  - `GET /api/study/{studyId}/members` - 스터디 멤버 목록 조회
  - `POST /api/study/{studyId}/apply` - 스터디 가입 신청
  - `POST /api/study/{studyId}/approve` - 스터디 가입 승인 (스터디장만 가능)
  - `POST /api/study/{studyId}/leave` - 스터디 탈퇴 (스터디장 제외)
