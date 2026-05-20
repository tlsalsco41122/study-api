package hs.dgsw.study_api.service;

import hs.dgsw.study_api.config.jwt.JwtProperties;
import hs.dgsw.study_api.config.jwt.TokenProvider;
import hs.dgsw.study_api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    @Override
    @Transactional(readOnly = true)
    public String createNewAccessToken(String refreshToken) throws Exception {
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalAccessException("유효하지 않은 토큰입니다.");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);
        Duration accessExpiration = Duration.ofMinutes(Long.parseLong(jwtProperties.getAccessExpirationMinutes()));

        return tokenProvider.generateToken(user, accessExpiration);
    }
}
