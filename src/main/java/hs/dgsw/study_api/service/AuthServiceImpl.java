package hs.dgsw.study_api.service;

import hs.dgsw.study_api.config.jwt.JwtProperties;
import hs.dgsw.study_api.config.jwt.TokenProvider;
import hs.dgsw.study_api.domain.User;
import hs.dgsw.study_api.dto.req.SignInReq;
import hs.dgsw.study_api.dto.req.SignUpReq;
import hs.dgsw.study_api.dto.res.SignInRes;
import hs.dgsw.study_api.dto.res.UserRes;
import hs.dgsw.study_api.repository.UserRepository;
import hs.dgsw.study_api.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public User signUp(SignUpReq req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        return userRepository.save(user);

    }

    @Override
    @Transactional
    public SignInRes signIn(SignInReq req) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        User user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();

        Duration accessTokenExpire = Duration.ofMinutes(Long.parseLong(jwtProperties.getAccessExpirationMinutes()));
        Duration refreshTokenExpire = Duration.ofDays(Long.parseLong(jwtProperties.getRefreshExpirationDays()));

        String accessToken = tokenProvider.generateToken(user, accessTokenExpire);
        String refreshToken = tokenProvider.generateToken(user, refreshTokenExpire);

        refreshTokenService.saveOrUpdate(user.getId(), refreshToken);

        return new SignInRes(accessToken, refreshToken);
    }
}
