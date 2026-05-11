package hs.dgsw.study_api.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRes {
    private String accessToken;
    private String refreshToken;
}
