package hs.dgsw.study_api.controller;

import hs.dgsw.study_api.dto.req.CreateAccessTokenReq;
import hs.dgsw.study_api.dto.res.CreateAccessTokenRes;
import hs.dgsw.study_api.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CreateAccessTokenRes> createNewAccessToken(@RequestBody CreateAccessTokenReq req) throws Exception {
        String newAccessToken = tokenService.createNewAccessToken(req.getRefreshToken());
        return ResponseEntity.ok(new CreateAccessTokenRes(newAccessToken));
    }
}
