package hs.dgsw.study_api.service;

import hs.dgsw.study_api.domain.RefreshToken;

public interface RefreshTokenService {
    RefreshToken findByRefreshToken(String refreshToken);
    RefreshToken saveOrUpdate(Long userId, String refreshToken);
}
