package hs.dgsw.study_api.controller;

import hs.dgsw.study_api.domain.User;
import hs.dgsw.study_api.dto.req.SignInReq;
import hs.dgsw.study_api.dto.req.SignUpReq;
import hs.dgsw.study_api.dto.res.SignInRes;
import hs.dgsw.study_api.dto.res.UserRes;
import hs.dgsw.study_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserRes> signUp(@RequestBody SignUpReq req) {
        User user = authService.signUp(req);
        UserRes userRes = new UserRes(user.getId(), user.getUsername());

        return ResponseEntity.ok(userRes);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInRes> signIn(@RequestBody SignInReq req) {
        SignInRes signInRes = authService.signIn(req);
        return ResponseEntity.ok(signInRes);
    }

}
