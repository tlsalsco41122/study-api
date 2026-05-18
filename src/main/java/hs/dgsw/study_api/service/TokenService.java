package hs.dgsw.study_api.service;

public interface TokenService {
    String createNewAccessToken(String refreshToken) throws Exception;
}
