package hs.dgsw.study_api.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenReq {
    private String refreshToken;
}
