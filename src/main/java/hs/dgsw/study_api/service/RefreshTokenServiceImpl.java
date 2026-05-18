package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.RefreshToken;
import hs.dgsw.study_api.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

    @Transactional
    @Override
    public RefreshToken saveOrUpdate(Long userId, String refreshToken) {
        return refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(refreshToken))
                .map(refreshTokenRepository::save)
                .orElseGet(() ->
                        refreshTokenRepository.save(new RefreshToken(userId, refreshToken))
                );
    }
}
